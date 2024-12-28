package com.nasahacker.library.model

import android.graphics.Bitmap

enum class ImageFormat(val ext: String) {

    JPEG(".jpg"), PNG(".png"), WEBP(".webp");

    companion object {
        fun getCompressionFormat(format: ImageFormat): Bitmap.CompressFormat {
            return when (format) {
                JPEG -> Bitmap.CompressFormat.JPEG
                PNG -> Bitmap.CompressFormat.PNG
                WEBP -> Bitmap.CompressFormat.WEBP
            }
        }

        fun getExtension(format: ImageFormat): String = format.ext
    }


}