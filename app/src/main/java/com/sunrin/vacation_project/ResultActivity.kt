package com.sunrin.vacation_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sunrin.vacation_project.databinding.ActivityLoginBinding
import com.sunrin.vacation_project.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val key_value = intent
        Log.e("intent_value", key_value.getIntExtra("Success_Value",7).toString())
        when(key_value.getIntExtra("Success_Value", 0)){
            0 ->  supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, rent_failed())
                .commit()
            1 -> supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, rent_success())
                .commit()
            2 -> supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, rent_return())
                .commit()
        }
    }
}