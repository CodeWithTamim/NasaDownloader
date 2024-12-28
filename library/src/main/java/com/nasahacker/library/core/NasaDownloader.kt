package com.nasahacker.library.core

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Environment
import androidx.annotation.Keep
import androidx.core.app.ActivityCompat
import com.nasahacker.library.model.ImageFormat
import com.nasahacker.library.model.ImageFormat.Companion.getCompressionFormat
import com.nasahacker.library.model.ImageFormat.Companion.getExtension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 * A singleton class responsible for downloading and saving images from URLs.
 * This class supports saving images in various formats and managing permissions for accessing external storage.
 */
@Keep
class NasaDownloader private constructor(
    private val saveDirName: String,
    private val imageQuality: Int,
) {

    companion object {
        private const val DEFAULT_DIR_NAME = "NasaDownloader"
        private const val DEFAULT_IMAGE_QUALITY = 100

        @Volatile
        private var instance: NasaDownloader? = null

        /**
         * Returns the singleton instance of NasaDownloader.
         * If the instance does not exist, it will be created.
         *
         * @param saveDirName The name of the directory to save images in (default is "NasaDownloader").
         * @param imageQuality The quality of the saved image (default is 100).
         * @return The singleton instance of NasaDownloader.
         */
        @JvmStatic
        fun getInstance(
            saveDirName: String = DEFAULT_DIR_NAME,
            imageQuality: Int = DEFAULT_IMAGE_QUALITY,
        ): NasaDownloader {
            return instance ?: synchronized(this) {
                instance ?: NasaDownloader(saveDirName, imageQuality).also { instance = it }
            }
        }
    }

    private val rootPath: String =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()

    /**
     * Downloads an image from the provided URL and saves it in the specified format.
     * This function runs in a coroutine context to avoid blocking the main thread.
     *
     * @param url The URL of the image to download.
     * @param format The format in which the image will be saved (e.g., JPEG, PNG).
     * @param onSuccess A lambda function to execute if the image is downloaded and saved successfully.
     * @param onFailure A lambda function to execute if there is an error during the download or save process.
     */
    suspend fun downloadImageFromUrl(
        url: String,
        format: ImageFormat,
        onSuccess: (File) -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        try {
            val bitmap = loadBitmapFromUrl(url)
            val file = saveImage(bitmap, format)
            withContext(Dispatchers.Main) {
                onSuccess(file)
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                onFailure(e)
            }
        }
    }

    /**
     * Saves a provided bitmap image in the specified format.
     * This function runs in a coroutine context to avoid blocking the main thread.
     *
     * @param bitmap The image bitmap to save.
     * @param format The format in which the image will be saved (e.g., JPEG, PNG).
     * @param onSuccess A lambda function to execute if the image is saved successfully.
     * @param onFailure A lambda function to execute if there is an error during the save process.
     */
    suspend fun saveBitmap(
        bitmap: Bitmap,
        format: ImageFormat,
        onSuccess: (File) -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        try {
            val file = saveImage(bitmap, format)
            withContext(Dispatchers.Main) {
                onSuccess(file)
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                onFailure(e)
            }


        }

    }

    /**
     * Loads an image from the specified URL.
     * This function runs in an IO dispatcher to prevent blocking the main thread during network operations.
     *
     * @param url The URL of the image to load.
     * @return A [Bitmap] representing the image loaded from the URL.
     */
    private suspend fun loadBitmapFromUrl(url: String): Bitmap {
        return withContext(Dispatchers.IO) {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            connection.inputStream.use { input ->
                BitmapFactory.decodeStream(input)
            }
        }
    }

    /**
     * Saves a bitmap image to the device's external storage in the specified format.
     * This function runs in an IO dispatcher to prevent blocking the main thread during file operations.
     *
     * @param bitmap The bitmap image to save.
     * @param format The format in which the image will be saved (e.g., JPEG, PNG).
     * @return A [File] representing the saved image.
     */
    private suspend fun saveImage(bitmap: Bitmap, format: ImageFormat): File {
        return withContext(Dispatchers.IO) {
            val directory = File("$rootPath/$saveDirName")
            if (!directory.exists()) {
                directory.mkdirs()
            }

            val fileName = "${System.currentTimeMillis()}${getExtension(format)}"
            val file = File(directory, fileName)

            FileOutputStream(file).use { fos ->
                bitmap.compress(getCompressionFormat(format), imageQuality, fos)
                fos.flush()
            }

            file
        }
    }

    /**
     * Checks if the necessary permissions are granted to read or write images to external storage.
     *
     * @param activity The activity from which the permission is being checked.
     * @return A Boolean indicating whether the required permissions are granted.
     */
    fun isPermissionsGranted(activity: Activity): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.checkSelfPermission(
                activity, Manifest.permission.READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            ActivityCompat.checkSelfPermission(
                activity, Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    /**
     * Requests the necessary permissions to read or write images to external storage.
     * This function handles permissions for both newer and older Android versions.
     *
     * @param activity The activity from which the permission request is initiated.
     */
    fun requestPermissions(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                activity, arrayOf(Manifest.permission.READ_MEDIA_IMAGES), 453
            )
        } else {
            ActivityCompat.requestPermissions(
                activity, arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), 453
            )
        }
    }
}
