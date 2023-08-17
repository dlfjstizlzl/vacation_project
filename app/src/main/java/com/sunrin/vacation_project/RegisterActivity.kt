package com.sunrin.vacation_project

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sunrin.vacation_project.databinding.ActivityRegisterBinding
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.back.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }

        binding.loginedittext.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val email = binding.loginedittext.text.toString()
                val pattern: Pattern = Patterns.EMAIL_ADDRESS
                if (pattern.matcher(email).matches()) {
                    binding.loginedittext.setBackgroundResource(R.drawable.et_blackoutline)
                    binding.loginedittext.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mailblack, 0, 0, 0)
                }
                else {
                    binding.loginedittext.setBackgroundResource(R.drawable.et_redoutline)
                    binding.loginedittext.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mailred, 0, R.drawable.error, 0)
                }
            }
            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }

        })
        binding.passwordedittext.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val password = binding.passwordedittext.text.toString()
                if (password.isNotEmpty()) {
                    binding.passwordedittext.setBackgroundResource(R.drawable.et_blackoutline)
                    binding.passwordedittext.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lockblack, 0, 0, 0)
                }
                else {
                    binding.passwordedittext.setBackgroundResource(R.drawable.et_redoutline)
                    binding.passwordedittext.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lockred, 0, R.drawable.error, 0)
                }
            }
            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }

        })
        binding.repasswordedittext.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val password = binding.passwordedittext.text.toString()
                val repassword = binding.repasswordedittext.text.toString()
                if (password == repassword) {
                    binding.repasswordedittext.setBackgroundResource(R.drawable.et_blackoutline)
                    binding.repasswordedittext.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lockblack, 0, 0, 0)
                }
                else {
                    binding.repasswordedittext.setBackgroundResource(R.drawable.et_redoutline)
                    binding.repasswordedittext.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lockred, 0, R.drawable.error, 0)
                }
            }
            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }

        })

        binding.registerbutton.setOnClickListener {
            val email = binding.loginedittext.text.toString()
            val pass = binding.passwordedittext.text.toString()
            val confirmPass = binding.repasswordedittext.text.toString()
            val intent_phonenumber = intent.getStringExtra("phonenumber").toString()
            val intent_birthday = intent.getStringExtra("birthday").toString()
            val intent_name = intent.getStringExtra("username").toString()
            val pattern: Pattern = Patterns.EMAIL_ADDRESS
            if (pattern.matcher(email).matches()) {
                if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty())
                {
                    if (pass == confirmPass) {
                        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {                 //파이어베이스 회원가입
                            if (it.isSuccessful) {
                                Toast.makeText(this, "계정이 생성되었습니다.", Toast.LENGTH_SHORT).show()
                                register(email, intent_birthday, intent_name, intent_phonenumber)
                                val intent = Intent(this, mapActivity::class.java)
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
            else {
                Toast.makeText(this, "비밀번호 형식이 맞지 않습니다.", Toast.LENGTH_SHORT).show()

            }

        }


    }
    fun register(email : String, birthday : String, username : String, phonenumber : String) {
        val user = hashMapOf<String, String>(
            "email" to email,
            "birthday" to birthday,
            "username" to username,
            "phonenumber" to phonenumber
        )

        db.collection("user").document(email)
            .set(user)
    }
}