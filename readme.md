
# Nasa Downloader Library

**Nasa Downloader** is a lightweight and efficient library designed to simplify downloading and saving images from URLs or Bitmaps, especially for Android 13 and later. It supports **API 21 to API 34**.

## Why Use This Library?

Many developers face issues when saving or downloading images on Android 13 and above. This library provides an easy-to-use solution for handling these tasks efficiently, making it a great choice for your Android projects.

## 📚 Documentation

Follow these steps to integrate **Nasa Downloader** into your Android project.

---

### Step 0: Add the JitPack Repository

To include this library in your project, you need to add the JitPack repository in your Gradle configuration.

#### `settings.gradle` (Groovy)
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // other repositories
        maven { url 'https://jitpack.io' }  // add JitPack repository
    }
}
```

#### `settings.gradle.kts` (Kotlin)
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // other repositories
        maven(url = "https://jitpack.io")  // add JitPack repository
    }
}
```

---

### Step 1: Add the Nasa Downloader Dependency

#### `build.gradle` (App-level) (Groovy)
```groovy
dependencies {
    implementation 'com.github.CodeWithTamim:NasaDownloader:1.0.0'
}
```

#### `build.gradle.kts` (App-level) (Kotlin)
```kotlin
dependencies {
    implementation("com.github.CodeWithTamim:NasaDownloader:1.0.0")
}
```

---

### Step 2: Add Required Permissions

To ensure proper functionality, add these permissions to your `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.READ_MEDIA_IMAGES"/>
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```

If your minimum SDK is not 21, add the following to your `AndroidManifest.xml`:

```xml
<uses-sdk android:minSdkVersion="your_min_sdk" tools:overrideLibrary="com.nasahacker.downloader" />
```

---

### Step 3: Initializing Nasa Downloader

Create an instance of the `NasaDownloader` class by passing the directory name where the image will be saved.

#### Java Example:
```java
NasaDownloader downloader = new NasaDownloader("SavingDirName");
```

#### Kotlin Example:
```kotlin
val downloader = NasaDownloader("SavingDirName")
```

---

### Step 4: Check for Permissions

Before downloading or saving images, ensure that the necessary permissions are granted.

#### Java Example:
```java
if (!downloader.isPermissionGranted(this)) {
    downloader.requestPermission(this);
} else {
    // Ready to download image
}
```

#### Kotlin Example:
```kotlin
if (!downloader.isPermissionGranted(this)) {
    downloader.requestPermission(this)
} else {
    // Ready to download image
}
```

---

### Step 5: Download or Save an Image

There are two ways to download or save an image:
1. Using a **URL**.
2. Using a **Bitmap**.

#### Download Using Bitmap

To download an image using a **Bitmap**, call the `downloadImage` method and pass the required arguments (`Activity context`, `Bitmap`, and `Save Type`).

##### Java Example:
```java
downloader.downloadImage(this, bitmapImage, NasaDownloader.IMAGE_TYPE.JPEG);
```

##### Kotlin Example:
```kotlin
downloader.downloadImage(this, bitmapImage, NasaDownloader.IMAGE_TYPE.JPEG)
```

---

### Extracting Bitmap from ImageView

You can get the Bitmap from an ImageView using the following code:

##### Java:
```java
Drawable drawable = yourImageView.getDrawable();
BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
Bitmap myBitmap = bitmapDrawable.getBitmap();
```

##### Kotlin:
```kotlin
val drawable = yourImageView.drawable
val bitmapDrawable = drawable as? BitmapDrawable
val myBitmap = bitmapDrawable?.bitmap
```

---

### Download Using URL

To download an image using a **URL**, call the `downloadImage` method and pass the required arguments (`Activity context`, `Image URL`, and `Save Type`).

##### Java Example:
```java
downloader.downloadImage(this, "https://image.com/image.png", NasaDownloader.IMAGE_TYPE.JPEG);
```

##### Kotlin Example:
```kotlin
downloader.downloadImage(this, "https://image.com/image.png", NasaDownloader.IMAGE_TYPE.JPEG)
```

---

### Customizing Success and Failure Messages

By default, after downloading and saving the image, a `success` or `failure` toast message will be displayed. You can customize these messages as follows:

##### Java Example:
```java
downloader.setSuccessMsg("Image downloaded successfully!");
downloader.setFailureMsg("Image download failed!");
```

##### Kotlin Example:
```kotlin
downloader.setSuccessMsg("Image downloaded successfully!")
downloader.setFailureMsg("Image download failed!")
```

---

### Final Code Example

Here's an example of the full code to download an image:

#### Java:
```java
NasaDownloader downloader = new NasaDownloader("SavingDirName");
downloader.setSuccessMsg("Image downloaded successfully!");
downloader.setFailureMsg("Image download failed!");
if (!downloader.isPermissionGranted(this)) {
    downloader.requestPermission(this);
} else {
    downloader.downloadImage(this, "https://image.com/image.png", NasaDownloader.IMAGE_TYPE.JPEG);
}
```

#### Kotlin:
```kotlin
val downloader = NasaDownloader("SavingDirName")
downloader.setSuccessMsg("Image downloaded successfully!")
downloader.setFailureMsg("Image download failed!")
if (!downloader.isPermissionGranted(this)) {
    downloader.requestPermission(this)
} else {
    downloader.downloadImage(this, "https://image.com/image.png", NasaDownloader.IMAGE_TYPE.JPEG)
}
```

---

## Contributing

If the **Nasa Downloader** library helped you, consider giving it a star and sharing it with your fellow developers. Contributions are always welcome! If you find any issues or want to suggest improvements, fork the project, make your changes, and submit a pull request.

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for more details.

---

Thanks for reading the documentation! I’m **Tamim**, the creator of this library. If you have any questions or need further assistance, feel free to [open an issue](https://github.com/CodeWithTamim/NasaDownloader/issues) or [email me](mailto:tamimh.dev@gmail.com).
