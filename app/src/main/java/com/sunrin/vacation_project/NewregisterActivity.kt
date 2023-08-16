package com.sunrin.vacation_project

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.google.android.play.core.integrity.v
import com.google.firebase.auth.FirebaseAuth
import com.sunrin.vacation_project.databinding.ActivityMapBinding
import com.sunrin.vacation_project.databinding.ActivityNewregisterBinding

class NewregisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewregisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewregisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        firebaseAuth = FirebaseAuth.getInstance()


        binding.back.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }
        binding.inputEdittext.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val email = binding.inputEdittext.text.toString()
                val pattern = android.util.Patterns.EMAIL_ADDRESS
                if (!pattern.matcher(email).matches()) {
                    binding.inputEdittext.setBackgroundResource(R.drawable.et_redoutline)
                    binding.inputEdittext.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mailred, 0, R.drawable.error, 0)
                }
                else {
                    binding.inputEdittext.setBackgroundResource(R.drawable.et_grayoutline)
                    binding.inputEdittext.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mail, 0, 0, 0)

                }
            }
            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }

        })
        binding.nextbutton.setOnClickListener {

            if (page == 1)
            {
                val email = binding.inputEdittext.text.toString()
                if (email.isNotEmpty())  {
                    val patter = android.util.Patterns.EMAIL_ADDRESS
                    if (patter.matcher(email).matches()) {
                        page = 2
                    }
                    else {
                    }
                }
                else{
                    Toast.makeText(this, "이메일을 입력해 주세요.", Toast.LENGTH_SHORT).show()
                }
            }
            else if (page == 2)
            {
                val password = binding.inputEdittext.text.toString()
                if (password.isNotEmpty())  {
                    page = 3
                }
                else{
                    Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
            }

        }








    }
}