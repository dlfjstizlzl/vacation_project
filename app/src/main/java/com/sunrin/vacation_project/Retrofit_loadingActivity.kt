package com.sunrin.vacation_project

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class Retrofit_loadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_loading)
        val db = Firebase.firestore
        //레트로핏2 통신
        RetrofitClass.getInstance()?.getUserPage(1234)?.enqueue(object: Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val intent_nextActivity = Intent(this@Retrofit_loadingActivity, ResultActivity::class.java)
                if(response.isSuccessful){
                    // 정상적으로 통신이 성공된 경우
                    var result: User? = response.body()
                    Log.d("Retrofit2", "onResponse2 성공: " + result.toString());
                    //val emailValue: String
                    //emailValue = intent.getStringExtra("email").toString()
                    Log.d("emailValue", email)
                    val tumbler_num = db.collection("user").document(email)
                    val tumblerValue: Int = result!!.tembler_num
                    tumbler_num
                        .update("tumbler_num", tumblerValue)
                        .addOnSuccessListener { Log.d("firebase", "DocumentSnapshot successfully updated!") }
                        .addOnFailureListener { Log.d("firebase", "Error updating document") }
                    intent_nextActivity.putExtra("Success_Value", 1)
                }else{
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    Log.d("Retrofit2", "onResponse2 실패")
                    intent_nextActivity.putExtra("Success_Value", 0)
                }
                startActivity(intent_nextActivity)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("YMC", "onFailure2 에러: " + t.message.toString());

            }
        })

    }

}

