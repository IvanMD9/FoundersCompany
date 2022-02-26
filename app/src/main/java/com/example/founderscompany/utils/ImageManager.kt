package com.example.founderscompany.utils

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.InputStream

object ImageManager {
    fun getBitmapFromAssets(assetManager: AssetManager, path : String) : Bitmap {
        val inputStream : InputStream = assetManager.open(path)
        return BitmapFactory.decodeStream(inputStream)
    }
}