package com.sunrin.vacation_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        val value_fb = db.collection("user").document(locked_email)

        value_fb.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("fb_test", "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d("fb_test", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("fb_test", "get failed with ", exception)
            }
    }
}