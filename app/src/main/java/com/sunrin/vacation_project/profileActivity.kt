package com.sunrin.vacation_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class profileActivity : AppCompatActivity() {
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val profile_name = findViewById<TextView>(R.id.profile_name)
        val profile_email = findViewById<TextView>(R.id.profile_email)
        val status_txt = findViewById<TextView>(R.id.status_txt)
        val value_fb = db.collection("user").document(locked_email)
        val return_btn = findViewById<TextView>(R.id.return_map)
        value_fb.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("fb_test", "DocumentSnapshot data: ${document.data}")
                    profile_email.setText(document.getString("email"))
                    profile_name.setText(document.getString("username"))
                if(document.getDouble("tumbler_num") != null){
                        status_txt.setText("대여중")
                    }else{
                        status_txt.setText("미대여")
                    }
                } else {
                    Log.d("fb_test", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("fb_test", "get failed with ", exception)
            }
        return_btn.setOnClickListener {
            val intent = Intent(this, mapActivity::class.java)
            startActivity(intent)
        }
    }
}