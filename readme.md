# Nasa Downloader
[![Latest Version](https://jitpack.io/v/CodeWithTamim/NasaDownloader.svg)](https://jitpack.io/#CodeWithTamim/NasaDownloader)
![GitHub Stars](https://img.shields.io/github/stars/CodeWithTamim/NasaDownloader)

Nasa Downloader is a library designed to download images from URLs or bitmaps and save them on an Android device. It supports saving images in various formats and manages permissions for accessing external storage.

## Features

- Download images from URLs
- Save images from bitmaps
- Supports multiple image formats (JPEG, PNG, etc.)
- Manages permissions for external storage access

## Installation

To use this library, add the following dependency to your `build.gradle` or `build.gradle.kts` file. The library is hosted on JitPack.

### Groovy

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.CodeWithTamim:NasaDownloader:1.0.0'
}
```

### Kotlin

```kotlin
allprojects {
    repositories {
        ...
        maven { url = uri("https://jitpack.io") }
    }
}

dependencies {
    implementation("com.github.CodeWithTamim:NasaDownloader:1.0.0")
}
```

## Usage

### Initialization

To get the singleton instance of `NasaDownloader`, use the following code:

```kotlin
val nasaDownloader = NasaDownloader.getInstance(
    saveDirName = "MyImages", // Optional, default is "NasaDownloader"
    imageQuality = 80 // Optional, default is 100
)
```

### Download Image from URL

To download an image from a URL and save it:
## Java
```java

 ExecutorService executorService = Executors.newSingleThreadExecutor(); // Create a single-threaded executor

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    nasaDownloader.downloadImageFromUrl(
                        "https://example.com/image.jpg",
                        ImageFormat.JPEG,
                        new DownloadCallback() {
                            @Override
                            public void onSuccess(File file) {
                                // Handle success
                            }

                            @Override
                            public void onFailure(Exception exception) {
                                // Handle failure
                            }
                        }
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        executorService.shutdown(); // Shut down the executor after task submission
    }

```

## Kotlin

```kotlin
lifecycleScope.launch(Dispatchers.IO) {
nasaDownloader.downloadImageFromUrl(
    url = "https://example.com/image.jpg",
    format = ImageFormat.JPEG,
    onSuccess = { file ->
        // Handle success
    },
    onFailure = { exception ->
        // Handle failure
    }
)
}
```

### Save Bitmap

To save a bitmap image:
## Java


```java

 ExecutorService executorService = Executors.newSingleThreadExecutor(); // Create a single-threaded executor

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    nasaDownloader.saveBitmap(
                        myBitmap,
                        ImageFormat.PNG,
                        new DownloadCallback() {
                            @Override
                            public void onSuccess(File file) {
                                // Handle success
                            }

                            @Override
                            public void onFailure(Exception exception) {
                                // Handle failure
                            }
                        }
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.shutdown(); // Shut down the executor after task submission
    }

```


## Kotlin

```kotlin
lifecycleScope.launch(Dispatchers.IO) {
nasaDownloader.saveBitmap(
    bitmap = myBitmap,
    format = ImageFormat.PNG,
    onSuccess = { file ->
        // Handle success
    },
    onFailure = { exception ->
        // Handle failure
    }
)
}
```

### Permissions


Add the following permissions to your `AndroidManifest.xml` file:

```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.READ_MEDIA_IMAGES"/>
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```

Check if the necessary permissions are granted:

```kotlin
if (!nasaDownloader.isPermissionsGranted(activity)) {
    nasaDownloader.requestPermissions(activity)
}
```


## License

This project is licensed under the [Apache 2.0 License](LICENSE).

## Contributing

Contributions are welcome! Please open an issue or submit a pull request [here](https://github.com/CodeWithTamim/NasaDownloader).

## Contact

For any inquiries, please contact tamimh.dev@gmail.com.

---

Made with ❤️ by Tamim Hossain
