
# 🚀 Nasa Downloader Library

**Nasa Downloader** is a lightweight and efficient library designed to simplify downloading and saving images from URLs or Bitmaps, especially for Android 13 and later. It supports **API 21 to API 34** and provides a smooth solution for image saving tasks in your Android projects.

## ✨ Why Choose This Library?

Many developers face issues when saving or downloading images on Android 13 and above. **Nasa Downloader** offers an intuitive API to handle these tasks effortlessly, ensuring compatibility and ease of use across Android versions.

## 📚 Integration Guide

### Step 0: Add the JitPack Repository

To integrate **Nasa Downloader** into your project, add the JitPack repository.

#### Groovy (settings.gradle):
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

#### Kotlin DSL (settings.gradle.kts):
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven(url = "https://jitpack.io")
    }
}
```

### Step 1: Add the Dependency

#### Groovy (build.gradle):
```groovy
dependencies {
    implementation 'com.github.CodeWithTamim:NasaDownloader:1.0.0'
}
```

#### Kotlin DSL (build.gradle.kts):
```kotlin
dependencies {
    implementation("com.github.CodeWithTamim:NasaDownloader:1.0.0")
}
```

### Step 2: Add Required Permissions

Ensure the following permissions are added to your `AndroidManifest.xml` to enable downloading and saving:

```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.READ_MEDIA_IMAGES"/>
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```

For minimum SDK versions above 21, include this:

```xml
<uses-sdk android:minSdkVersion="your_min_sdk" tools:overrideLibrary="com.nasahacker.downloader"/>
```

### Step 3: Initialize the Nasa Downloader

Create an instance of `NasaDownloader` by passing the directory name where images will be saved:

#### Java Example:
```java
NasaDownloader downloader = new NasaDownloader("YourDirectory");
```

#### Kotlin Example:
```kotlin
val downloader = NasaDownloader("YourDirectory")
```

### Step 4: Permission Handling

Before saving or downloading an image, ensure the required permissions are granted:

#### Java Example:
```java
if (!downloader.isPermissionGranted(this)) {
        downloader.requestPermission(this);
} else {
        // Ready to download or save images
        }
```

#### Kotlin Example:
```kotlin
if (!downloader.isPermissionGranted(this)) {
    downloader.requestPermission(this)
} else {
    // Ready to download or save images
}
```

### Step 5: Image Downloading

Download an image either by **URL** or **Bitmap**.

#### Downloading an Image via URL:

##### Java:
```java
downloader.downloadImage(this, "https://image.com/sample.png", NasaDownloader.IMAGE_TYPE.JPEG);
```

##### Kotlin:
```kotlin
downloader.downloadImage(this, "https://image.com/sample.png", NasaDownloader.IMAGE_TYPE.JPEG)
```

#### Downloading an Image via Bitmap:

##### Java:
```java
downloader.downloadImage(this, bitmap, NasaDownloader.IMAGE_TYPE.JPEG);
```

##### Kotlin:
```kotlin
downloader.downloadImage(this, bitmap, NasaDownloader.IMAGE_TYPE.JPEG)
```

### Extracting Bitmap from ImageView

You can extract a Bitmap from an ImageView using the following code:

##### Java:
```java
Drawable drawable = imageView.getDrawable();
Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
```

##### Kotlin:
```kotlin
val bitmap = (imageView.drawable as? BitmapDrawable)?.bitmap
```

### Step 6: Customize Success & Failure Messages

Customize the toast messages displayed upon success or failure:

#### Java Example:
```java
downloader.setSuccessMsg("Download successful!");
downloader.setFailureMsg("Download failed!");
```

#### Kotlin Example:
```kotlin
downloader.setSuccessMsg("Download successful!")
downloader.setFailureMsg("Download failed!")
```

---

## Example Code (Full Workflow)

#### Java:
```java
NasaDownloader downloader = new NasaDownloader("YourDirectory");
downloader.setSuccessMsg("Image downloaded!");
downloader.setFailureMsg("Download failed!");
if (!downloader.isPermissionGranted(this)) {
        downloader.requestPermission(this);
} else {
        downloader.downloadImage(this, "https://image.com/sample.png", NasaDownloader.IMAGE_TYPE.JPEG);
}
```

#### Kotlin:
```kotlin
val downloader = NasaDownloader("YourDirectory")
downloader.setSuccessMsg("Image downloaded!")
downloader.setFailureMsg("Download failed!")
if (!downloader.isPermissionGranted(this)) {
    downloader.requestPermission(this)
} else {
    downloader.downloadImage(this, "https://image.com/sample.png", NasaDownloader.IMAGE_TYPE.JPEG)
}
```

---

## Contributing

If **Nasa Downloader** has been useful to you, please give it a ⭐ and consider sharing it with your peers. Contributions are always welcome! Fork the repository, make your changes, and submit a pull request.

## License

Licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for more information.

---

Thanks for using **Nasa Downloader Library**! For any questions, feel free to [open an issue](https://github.com/CodeWithTamim/NasaDownloader/issues) or [email me](mailto:tamimh.dev@gmail.com).
