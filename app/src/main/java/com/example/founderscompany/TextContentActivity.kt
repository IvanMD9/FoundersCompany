package com.example.founderscompany

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TextContentActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var imageView: ImageView
    private var category : Int = 0
    private var position : Int = 0

    private var arrayTechnoText = listOf(
        R.string.google, R.string.apple, R.string.yandex,
        R.string.microsoft, R.string.amazon, R.string.meta,
        R.string.netflix, R.string.youtube, R.string.brains
    )
    private var arrayTechnoImage = listOf(
        R.drawable.found_google, R.drawable.found_apple, R.drawable.found_yandex,
        R.drawable.found_microsoft, R.drawable.found_amazon, R.drawable.found_meta,
        R.drawable.found_netflix, R.drawable.found_youtube, R.drawable.found_brains
    )
   private var arrayCarText = listOf(
       R.string.tesla, R.string.ford, R.string.merc,
       R.string.bmw, R.string.toyota,R.string.wagen,
       R.string.honda, R.string.gm,R.string.hyundai, R.string.nissan
   )

    private var arrayCarImage = listOf(
        R.drawable.found_tesla, R.drawable.found_ford, R.drawable.found_merc,
        R.drawable.found_bmw, R.drawable.found_toyota, R.drawable.found_wagen,
        R.drawable.found_honda, R.drawable.found_gm, R.drawable.found_hyundai, R.drawable.found_nissan
    )
    private var arrayBankText = listOf(
       R.string.bankOfAmerica, R.string.morgan
    )

    private var arrayBankImage = listOf(
        R.drawable.found_of_america, R.drawable.morgan
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.text_content)
        textView = findViewById(R.id.tv_content)
        imageView = findViewById(R.id.iv_content)
        receiveIntent()
    }

     private fun receiveIntent() {

         val intent = intent

         if (intent != null) {
             category = intent.getIntExtra("category", 0)
             position = intent.getIntExtra("position", 0)
         }

         when (category) {

             0 -> {
                 imageView.setImageResource(arrayTechnoImage[position])
                 textView.setText(arrayTechnoText[position])
             }

             1 -> {
                 imageView.setImageResource(arrayCarImage[position])
                 textView.setText(arrayCarText[position])
             }

             2 -> {
                 imageView.setImageResource(arrayBankImage[position])
                 textView.setText(arrayBankText[position])
             }

             3 -> {

             }
         }
     }
}