package com.nasahacker.downloader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
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
    private String saveDirName;
    private String successMsg = "Success";
    private String failureMsg = "Failure";
    private boolean overwriteExisting = false; // New feature to handle overwriting files
    private int imageQuality = 100; // New feature for custom image quality

    public NasaDownloader(String fileDirName) {
        this.saveDirName = fileDirName;
    }

    public void setSaveDirName(String dirName) {
        this.saveDirName = dirName; // Dynamically set directory
    }

    public void downloadImage(Activity context, String url, IMAGE_TYPE type, DownloadCallback callback) {
        if (url == null || url.isEmpty()) {
            throw new NullPointerException("URL cannot be null or empty");
        }

        new Thread(() -> {
            try {
                Bitmap bitmap = downloadImageFromUrl(url);
                if (bitmap != null) {
                    saveImage(context, bitmap, type, callback);
                } else {
                    context.runOnUiThread(() -> {
                        Toast.makeText(context, failureMsg, Toast.LENGTH_SHORT).show();
                        callback.onFailure(new IOException("Failed to download image"));
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
                context.runOnUiThread(() -> {
                    Toast.makeText(context, failureMsg, Toast.LENGTH_SHORT).show();
                    callback.onFailure(e);
                });
            }
        }).start();
    }

    public void downloadImage(Activity context, Bitmap bitmap, IMAGE_TYPE type, DownloadCallback callback) {
        if (bitmap == null) {
            throw new NullPointerException("Bitmap cannot be null");
        }
        saveImage(context, bitmap, type, callback);
    }

    private Bitmap downloadImageFromUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.connect();

        try (InputStream inputStream = connection.getInputStream()) {
            return BitmapFactory.decodeStream(inputStream);
        }
    }

    private void saveImage(Activity context, Bitmap bitmap, IMAGE_TYPE type, DownloadCallback callback) {
        File directory = new File(ROOT_PATH + "/" + saveDirName);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        int randomInt = new Random().nextInt(1000);
        String ext = getExtension(type);
        Bitmap.CompressFormat format = getCompressFormat(type);

        String fileName = randomInt + ext;
        File newFile = new File(directory, fileName);

        if (!overwriteExisting && newFile.exists()) {
            context.runOnUiThread(() -> {
                Toast.makeText(context, "File already exists", Toast.LENGTH_SHORT).show();
                callback.onFailure(new IOException("File already exists"));
            });
            return;
        }

        new Thread(() -> {
            try (FileOutputStream fos = new FileOutputStream(newFile)) {
                bitmap.compress(format, imageQuality, fos);
                fos.flush();
                context.runOnUiThread(() -> {
                    Toast.makeText(context, successMsg, Toast.LENGTH_SHORT).show();
                    callback.onSuccess(newFile);
                });
            } catch (Exception e) {
                e.printStackTrace();
                context.runOnUiThread(() -> {
                    Toast.makeText(context, failureMsg, Toast.LENGTH_SHORT).show();
                    callback.onFailure(e);
                });
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

    public void setOverwriteExisting(boolean overwriteExisting) {
        this.overwriteExisting = overwriteExisting;
    }

    public void setImageQuality(int quality) {
        if (quality < 0 || quality > 100) {
            throw new IllegalArgumentException("Image quality must be between 0 and 100");
        }
        this.imageQuality = quality;
    }

    private String getExtension(IMAGE_TYPE type) {
        switch (type) {
            case PNG:
                return ".png";
            case WEBP:
                return ".webp";
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
            default:
                return Bitmap.CompressFormat.JPEG;
        }
    }

    public enum IMAGE_TYPE {
        JPEG,
        PNG,
        WEBP
    }

    public interface DownloadCallback {
        void onSuccess(File file);

        void onFailure(Exception e);
    }
}
