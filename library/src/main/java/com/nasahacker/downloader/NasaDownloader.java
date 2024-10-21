package com.nasahacker.downloader;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * CodeWithTamim
 *
 * @developer Tamim Hossain
 * @mail tamimh.dev@gmail.com
 */
public class NasaDownloader {

    private static final String ROOT_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
    private static final int PERMISSION_CODE = 100;
    private final String saveDirName;
    private final int quality;  // Added quality setting
    private String successMsg = "Success";
    private String failureMsg = "Failure";

    public NasaDownloader(String fileDirName, int quality) {
        this.saveDirName = fileDirName;
        this.quality = Math.max(0, Math.min(quality, 100));  // Ensure quality is between 0 and 100
    }

    public void requestPermission(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_MEDIA_IMAGES}, PERMISSION_CODE);
        } else {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_CODE);
        }
    }

    public boolean isPermissionGranted(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED;
        } else {
            return ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }
    }

    public void downloadImage(Activity context, String url, IMAGE_TYPE type) {
        if (url == null || url.isEmpty()) {
            throw new NullPointerException("URL cannot be null or empty");
        }

        new Thread(() -> {
            try {
                Bitmap bitmap = loadBitmapFromUrl(url);
                saveImage(context, bitmap, type);
            } catch (IOException e) {
                e.printStackTrace();
                context.runOnUiThread(() -> Toast.makeText(context, failureMsg, Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    public void downloadImage(Activity context, Bitmap bitmap, IMAGE_TYPE type) {
        if (bitmap == null) {
            throw new NullPointerException("Bitmap cannot be null");
        }
        saveImage(context, bitmap, type);
    }

    private Bitmap loadBitmapFromUrl(String src) throws IOException {
        URL url = new URL(src);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.connect();
        try (InputStream input = connection.getInputStream()) {
            return BitmapFactory.decodeStream(input);
        }
    }

    private void saveImage(Activity context, Bitmap bitmap, IMAGE_TYPE type) {
        File directory = new File(ROOT_PATH + "/" + saveDirName);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        int randomInt = new Random().nextInt(1000);
        String ext = getExtension(type);
        Bitmap.CompressFormat format = getCompressFormat(type);

        String fileName = randomInt + ext;
        File newFile = new File(directory, fileName);

        new Thread(() -> {
            try (FileOutputStream fos = new FileOutputStream(newFile)) {
                bitmap.compress(format, quality, fos);  // Quality parameter used here
                fos.flush();
                context.runOnUiThread(() -> Toast.makeText(context, successMsg, Toast.LENGTH_SHORT).show());
            } catch (Exception e) {
                e.printStackTrace();
                context.runOnUiThread(() -> Toast.makeText(context, failureMsg, Toast.LENGTH_SHORT).show());
            }
            MediaScannerConnection.scanFile(context, new String[]{newFile.toString()}, null, (path, uri) -> {
                // ignore
            });
        }).start();
    }

    public void setFailureMsg(String failureMsg) {
        this.failureMsg = failureMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }

    private String getExtension(IMAGE_TYPE type) {
        switch (type) {
            case PNG:
                return ".png";
            case WEBP:
                return ".webp";
            case JPEG:
            default:
                return ".jpg";
        }
    }

    private Bitmap.CompressFormat getCompressFormat(IMAGE_TYPE type) {
        switch (type) {
            case PNG:
                return Bitmap.CompressFormat.PNG;
            case WEBP:
                return Bitmap.CompressFormat.WEBP;
            case JPEG:
            default:
                return Bitmap.CompressFormat.JPEG;
        }
    }

    public enum IMAGE_TYPE {
        JPEG,   // .jpg
        PNG,    // .png
        WEBP    // .webp
    }
}
