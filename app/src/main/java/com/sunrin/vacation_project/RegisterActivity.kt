package com.sunrin.vacation_project

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.sunrin.vacation_project.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginbutton.setOnClickListener {

            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }

        binding.registerbutton.setOnClickListener {
            val email = binding.loginedittext.text.toString()
            val pass = binding.passwordedittext.text.toString()
            val confirmPass = binding.repasswordedittext.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty())
            {
                if (pass == confirmPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {                 //파이어베이스 회원가입
                        if (it.isSuccessful) {
                            Toast.makeText(this, "계정이 생성되었습니다.", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else {
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "모든 칸을 입력해 주세요.", Toast.LENGTH_SHORT).show()
            }

        }


    }
}