package com.sunrin.vacation_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
        val qrnum = intent.getIntExtra("QR_value", 0)
        Log.d("qr_num", qrnum.toString())
        //레트로핏2 통신
        RetrofitClass.getInstance()?.getUserPage(qrnum)?.enqueue(object: Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                var intent_nextActivity = Intent(this@Retrofit_loadingActivity, ResultActivity::class.java)
                if(response.isSuccessful){
                    // 정상적으로 통신이 성공된 경우
                    var result: User? = response.body()
                    Log.d("Retrofit2", "onResponse2 성공: " + result.toString());
                    //val emailValue: String
                    //emailValue = intent.getStringExtra("email").toString()
                    if(result!!.Success == true){
                        val tumbler_num = db.collection("user").document(locked_email)
                        val tumblerValue: Int = result!!.tembler_num
                        tumbler_num
                            .update("tumbler_num", tumblerValue)
                            .addOnSuccessListener { Log.d("firebase", "DocumentSnapshot successfully updated!") }
                            .addOnFailureListener { Log.d("firebase", "Error updating document") }
                        intent_nextActivity.putExtra("Success_Value", 1)
                    }
                    else{
                        intent_nextActivity.putExtra("Success_Value", 0)
                    }

                }else{
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    Log.d("Retrofit2", "onResponse2 실패")
                }

                startActivity(intent_nextActivity)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("YMC", "onFailure2 에러: " + t.message.toString());
                var intent_nextActivity = Intent(this@Retrofit_loadingActivity, ResultActivity::class.java)
                intent_nextActivity.putExtra("Success_Value", 0)
                startActivity(intent_nextActivity)

            }
        })

    }

}

