package com.sunrin.vacation_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sunrin.vacation_project.databinding.ActivityLoginBinding
import com.sunrin.vacation_project.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val transaction = supportFragmentManager.beginTransaction()
        val key_value = intent
        when(key_value.getIntExtra("Success_Value", -1)){
            0 ->  supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, rent_failed())
                .commit()
            1 -> supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, rent_success())
                .commit()
        }
    }
}