package com.example.founderscompany.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.founderscompany.R
import com.example.founderscompany.utils.ImageManager

class TextContentActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var imageView: ImageView
    private var imagePath: String = ""
    private var position : Int = 0

    private var arrayTechnoText = listOf(
        R.string.google, R.string.apple, R.string.yandex,
        R.string.microsoft, R.string.amazon, R.string.meta,
        R.string.netflix, R.string.youtube, R.string.brains
    )
    private var arrayCarText = listOf(
        R.string.tesla, R.string.ford, R.string.merc,
        R.string.bmw, R.string.toyota, R.string.wagen,
        R.string.honda, R.string.gm, R.string.hyundai, R.string.nissan
    )
    private var arrayBankText = listOf(
        R.string.bankOfAmerica, R.string.morgan, R.string.agricultural,
        R.string.chase, R.string.wells, R.string.tinkoff
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.text_content)
        textView = findViewById(R.id.tv_content)
        imageView = findViewById(R.id.iv_content)
        actionBarBack()
        receiveIntent()
    }

     private fun receiveIntent() {
         val intent = intent

         if (intent != null) {
             imagePath = intent.getStringExtra("image").toString()
             position = intent.getIntExtra("position", 0)
             imageView.setImageBitmap(ImageManager.getBitmapFromAssets(assets, imagePath))
             textView.setText(arrayTechnoText[position])
         }
     }

    private fun actionBarBack() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}