# Thanks For Using Nasa Downloader Library
## Some developers are facing issues while saving or  downloading images from url or bitmap in android 13 or later so I built this library to make the work easy ! It `supports API 21 - API 34`.
## Follow the documentation below to know how to use the library

### Step 0: Add this to your project gradle
#### `settings.gradle`
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        ...
        //add maven
        maven { url 'https://jitpack.io' }
    }
}
```
#### `build.gradle` app level module
```groovy
dependencies 
{
 implementation 'com.github.CodeWithTamim:NasaDownloader:1.0'
}
```
### Step 1: Add These Permissions In the `AndroidManifest.xml` otherwise you may get some errors :(

```xml
 <uses-permission android:name="android.permission.INTERNET"/>
 <uses-permission android:name="android.permission.READ_MEDIA_IMAGES"/>
 <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
 <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```
### If your min sdk is not 21 or different then add this to the `AndroidManifest.xml`

```xml

<uses-sdk android:minSdkVersion="your_min_sdk" tools:overrideLibrary="com.nasahacker.downloader" />
```

### Create an object of the `NasaDownloader` class and pass the saving directory name into the constructor.
#### Example : 
#### Java
```java
 NasaDownloader downloader = new NasaDownloader("SavingDirName");
```

#### Kotlin
```kotlin
 val downloader = NasaDownloader("SavingDirName")
```

### Step 2: Check for permission.
#### Example :
#### Java
```java
 if (!downloader.isPermissionGranted(this))
 {
  downloader.requestPermission(this);
 } 
 else 
 {
  //work on this later
 } 
```

#### Kotlin
```kotlin
 if (!downloader.isPermissionGranted(this))
 {
  downloader.requestPermission(this);
 } 
 else 
 {
  //work on this later
 }
```

### Step 3: Download or Save the image.
#### We can download or save the image in 2 ways 
 * Using Url.
 * Using Bitmap.

### Download or Save using bitmap.

### Inside the else block call the `downloadImage` method and pass required arguments which are `Activity context` ,`Image Bitmap` and `Save Type` 
#### Example :
#### Java
```java
downloader.downloadImage(this,bitmapImage, NasaDownloader.IMAGE_TYPE.JPEG);
```

#### Kotlin
```kotlin
downloader.downloadImage(this,bitmapImage, NasaDownloader.IMAGE_TYPE.JPEG)
```

#### Available image saving types are these following
```java
NasaDownloader.IMAGE_TYPE.JPEG //jpg type
NasaDownloader.IMAGE_TYPE.PNG //png type
NasaDownloader.IMAGE_TYPE.WEBP //webp type
```

### Download or Save using Url.
### Inside the else block call the `downloadImage` method and pass required arguments which are `Activity context` ,`Image Url` and `Save Type` 
#### Example :
#### Java
```java
downloader.downloadImage(this,"https://image.com/image.png", NasaDownloader.IMAGE_TYPE.JPEG);
```

#### Kotlin
```kotlin
downloader.downloadImage(this,"https://image.com/image.png", NasaDownloader.IMAGE_TYPE.JPEG)
```

### Then just wait for the image to download and save. After download and saving is successful  then it will show a toast `success` else it will show toast with message `failure`. You can set your own message for success and failure like this
#### Example :
### Java
```java
downloader.setSuccessMsg("Your Success Message");
downloader.setFailureMsg("Your failure Message");
  ```
### Kotlin
```kotlin
downloader.setSuccessMsg("Your Success Message");
downloader.setFailureMsg("Your failure Message");
  ```

  ## Final code sample
 #### Example :
  ### Java
  ```java
  NasaDownloader downloader = new NasaDownloader("SavingDirName");
  downloader.setSuccessMsg("Image downloaded successfully !");
  downloader.setFailureMsg("Image download failed :(");
  if (!downloader.isPermissionGranted(this))
  {
    downloader.requestPermission(this);
  }
  else
  {
    downloader.downloadImage(this,"https://image.com/image.png",NasaDownloader.IMAGE_TYPE.JPEG);
  
  }
  ```

  ### Kotlin
  ```kotlin
  val downloader = new NasaDownloader("SavingDirName")
  downloader.setSuccessMsg("Image downloaded successfully !")
  downloader.setFailureMsg("Image download failed :(")
  if (!downloader.isPermissionGranted(this))
  {
    downloader.requestPermission(this)
  }
  else
  {
    downloader.downloadImage(this,"https://image.com/image.png",NasaDownloader.IMAGE_TYPE.JPEG)
  
  }
  ```

### Thanks for reading the documentation, I'm `Tamim`, I made this library and I'm the one who was helping you throughout the documentation :)
### If the library helped you out then please give it a start and share with your dev friends ! The project is open for contrubution so if you have any fixes or new feature enhancement then just fork it then make your changes create a new brach and then just hit a pull request.

## Thank you guys for your love and support
## If you have any queries or need help then just open a issue or  <a href="mailto:tamimh.dev@gmail.com">mail me</a> 
## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.


 



