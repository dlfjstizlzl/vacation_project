package com.sunrin.vacation_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nextBtn:Button = findViewById(R.id.nextBtn)
        nextBtn.setOnClickListener {
            var intent = Intent(this, mapActivity::class.java)
            startActivity(intent)
        }



    }
}