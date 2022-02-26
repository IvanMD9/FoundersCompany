package com.example.founderscompany.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.founderscompany.R

class LogoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo)
    }

     fun onClickGo(view : View) {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}