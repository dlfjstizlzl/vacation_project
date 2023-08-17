package com.sunrin.vacation_project

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.InputType.TYPE_CLASS_DATETIME
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
                if (page == 1) {
                    val phone = binding.inputEdittext.text.toString()
                    val pattern = android.util.Patterns.PHONE
                    if (!pattern.matcher(phone).matches()) {
                        binding.inputEdittext.setBackgroundResource(R.drawable.et_grayoutline)
                        binding.inputEdittext.setCompoundDrawablesWithIntrinsicBounds(R.drawable.smartphone_gray, 0, 0, 0)
                    }
                    else {
                        binding.inputEdittext.setBackgroundResource(R.drawable.et_blackoutline)
                        binding.inputEdittext.setCompoundDrawablesWithIntrinsicBounds(R.drawable.smartphone, 0, 0, 0)

                    }
                }
                else if (page == 2) {

                }
            }
            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }

        })
        binding.nextbutton.setOnClickListener {

            if (page == 1)
            {
                val phone = binding.inputEdittext.text.toString()
                if (phone.isNotEmpty())  {
                    val patter = android.util.Patterns.PHONE
                    if (patter.matcher(phone).matches()) {
                        page = 2
                        binding.title.text = "생년월일을 입력해주세요"
                        binding.subtitle.text = "생년월일"
                        binding.inputEdittext.filters= arrayOf<InputFilter>(InputFilter.LengthFilter(8))
                        binding.inputEdittext.hint = "생년월일 8자리"
                        binding.inputEdittext.text.clear()
                        binding.inputEdittext.setBackgroundResource(R.drawable.et_grayoutline)
                        binding.inputEdittext.setCompoundDrawablesWithIntrinsicBounds(R.drawable.event, 0, 0, 0)
                    }
                    else {
                        Toast.makeText(this, "전화번호를 입력해 주세요.", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this, "전화번호를 입력해 주세요.", Toast.LENGTH_SHORT).show()
                }
            }
            else if (page == 2)
            {
                val birthday = binding.inputEdittext.text.toString()
                if (birthday.isNotEmpty())  {
                    val patter = android.util.Patterns.PHONE
                    if (patter.matcher(birthday).matches()) {
                        page = 2
                        binding.title.text = "생년월일을 입력해주세요"
                        binding.subtitle.text = "생년월일"
                        binding.inputEdittext.setRawInputType(TYPE_CLASS_DATETIME)
                        binding.inputEdittext.filters= arrayOf<InputFilter>(InputFilter.LengthFilter(8))
                        binding.inputEdittext.hint = "생년월일 8자리"
                        binding.inputEdittext.text.clear()
                        binding.inputEdittext.setBackgroundResource(R.drawable.et_grayoutline)
                        binding.inputEdittext.setCompoundDrawablesWithIntrinsicBounds(R.drawable.event, 0, 0, 0)
                    }
                    else {
                        Toast.makeText(this, "전화번호를 입력해 주세요.", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this, "전화번호를 입력해 주세요.", Toast.LENGTH_SHORT).show()
                }
            }

        }








    }
}