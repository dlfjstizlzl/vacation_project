package com.sunrin.vacation_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.sunrin.vacation_project.databinding.ActivityLoginBinding
public var email:String = ""
class loginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginbutton.setOnClickListener {

            email = binding.loginedittext.text.toString()
            val pass = binding.passwordedittext.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener {                 //파이어베이스 회원가입
                        if (it.isSuccessful) {
                            Toast.makeText(this, "로그인을 성공하였습니다", Toast.LENGTH_SHORT).show()
                            val intent2 = Intent(this, Retrofit_loadingActivity::class.java)
                            intent2.putExtra("emailValue", email)
                            val intent = Intent(this, mapActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

            } else {
                Toast.makeText(this, "모든 칸을 입력해 주세요.", Toast.LENGTH_SHORT).show()

            }
        }
        binding.registerbutton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}