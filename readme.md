


# 🚀 Nasa Downloader Library

[![Latest Version](https://jitpack.io/v/CodeWithTamim/NasaDownloader.svg)](https://jitpack.io/#CodeWithTamim/NasaDownloader)
![GitHub Stars](https://img.shields.io/github/stars/CodeWithTamim/NasaDownloader)
![License](https://img.shields.io/github/license/CodeWithTamim/NasaDownloader)
![Android API](https://img.shields.io/badge/Android-API%2021--34-brightgreen)
![Contributions Welcome](https://img.shields.io/badge/Contributions-Welcome-brightgreen)
![Platform](https://img.shields.io/badge/Platform-Android-blue)

**Nasa Downloader** is a lightweight and efficient library designed to simplify downloading and saving images from URLs or Bitmaps, especially for Android 13 and later. It supports **API 21 to API 34** and provides a smooth solution for image saving tasks in your Android projects.

---

## ✨ Why Choose This Library?

Many developers face issues when saving or downloading images on Android 13 and above. **Nasa Downloader** offers an intuitive API to handle these tasks effortlessly, ensuring compatibility and ease of use across Android versions.

---

## 📚 Integration Guide

### Step 0: Add the JitPack Repository

To integrate **Nasa Downloader** into your project, add the JitPack repository.

#### Groovy (settings.gradle)
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

#### Kotlin DSL (settings.gradle.kts)
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven(url = "https://jitpack.io")
    }
}
```

### Step 1: Add the Dependency

#### Groovy (build.gradle)
```groovy
dependencies {
    implementation 'com.github.CodeWithTamim:NasaDownloader:1.0.5'
}
```

#### Kotlin DSL (build.gradle.kts)
```kotlin
dependencies {
    implementation("com.github.CodeWithTamim:NasaDownloader:1.0.5")
}
```

### Step 2: Add Required Permissions

Ensure the following permissions are added to your `AndroidManifest.xml`:

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

Create an instance of `NasaDownloader` by passing the directory name and quality setting (0 to 100):

#### Java Example:
```java
NasaDownloader downloader = new NasaDownloader("YourDirectory", 80);
```

#### Kotlin Example:
```kotlin
val downloader = NasaDownloader("YourDirectory", 80)
```

### Step 4: Permission Handling

Before saving or downloading an image, ensure required permissions are granted:

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

Download an image either by **URL** or **Bitmap**. Supported formats: **JPEG**, **PNG**, and **WEBP**.

#### Download via URL

**Java**:
```java
downloader.downloadImage(this, "https://image.com/sample.png", NasaDownloader.IMAGE_TYPE.JPEG);
```

**Kotlin**:
```kotlin
downloader.downloadImage(this, "https://image.com/sample.png", NasaDownloader.IMAGE_TYPE.JPEG)
```

#### Download via Bitmap

**Java**:
```java
downloader.downloadImage(this, bitmap, NasaDownloader.IMAGE_TYPE.JPEG);
```

**Kotlin**:
```kotlin
downloader.downloadImage(this, bitmap, NasaDownloader.IMAGE_TYPE.JPEG)
```

### Extracting Bitmap from ImageView

**Java**:
```java
Drawable drawable = imageView.getDrawable();
Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
```

**Kotlin**:
```kotlin
val bitmap = (imageView.drawable as? BitmapDrawable)?.bitmap
```

### Step 6: Customize Messages

Customize toast messages displayed upon success or failure:

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
NasaDownloader downloader = new NasaDownloader("YourDirectory", 80);
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
val downloader = NasaDownloader("YourDirectory", 80)
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

If **Nasa Downloader** has been useful to you, please give it a ⭐ and share it with your peers. Contributions are always welcome! Fork the repository, make your changes, and submit a pull request.

---

## License

Licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for more information.

---

Thanks for using **Nasa Downloader Library**! For any questions, feel free to [open an issue](https://github.com/CodeWithTamim/NasaDownloader/issues) or [email me](mailto:tamimh.dev@gmail.com).
