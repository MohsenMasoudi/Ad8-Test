package com.ad8.presentation.util.extentions

import android.graphics.Bitmap

fun Bitmap.getResizedBitmap( maxSize: Int=1000): Bitmap {
//    var width: Int = this.width
//    var height: Int = this.height
//    val bitmapRatio = width.toFloat() / height.toFloat()
//    if (bitmapRatio > 1) {
//        width = maxSize
//        height = (width / bitmapRatio).toInt()
//    } else {
//        height = maxSize
//        width = (height * bitmapRatio).toInt()
//    }
//    return Bitmap.createScaledBitmap(this, width, height, true)
    return this
}