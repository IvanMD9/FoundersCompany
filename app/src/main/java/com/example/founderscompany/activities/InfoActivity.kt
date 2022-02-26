package com.example.founderscompany.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import com.example.founderscompany.R

class InfoActivity : AppCompatActivity() {

    private lateinit var appleImage: ImageView
    private lateinit var googleImage: ImageView
    private lateinit var aramcoImage: ImageView
    private lateinit var brainsImage: ImageView
    private lateinit var mercedesImage: ImageView
    private lateinit var fordImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        actionBarBack()
        initImage()
    }

    private fun initImage() {
        appleImage = findViewById(R.id.iv_apple)
        googleImage = findViewById(R.id.iv_google)
        aramcoImage = findViewById(R.id.iv_aramco)
        mercedesImage = findViewById(R.id.iv_merc)
        brainsImage = findViewById(R.id.iv_brains)
        fordImage = findViewById(R.id.iv_ford)

        appleImage.setImageResource(R.drawable.apple)
        googleImage.setImageResource(R.drawable.google)
        aramcoImage.setImageResource(R.drawable.aramco)
        brainsImage.setImageResource(R.drawable.brains)
        mercedesImage.setImageResource(R.drawable.mercedes)
        fordImage.setImageResource(R.drawable.ford)
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