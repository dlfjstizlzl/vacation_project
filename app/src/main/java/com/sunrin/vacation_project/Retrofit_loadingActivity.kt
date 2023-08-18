package com.sunrin.vacation_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Retrofit_loadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_loading)

        //레트로핏2 통신
        RetrofitClass.getInstance()?.getUserPage(1234)?.enqueue(object: Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val intent = Intent(this@Retrofit_loadingActivity, ResultActivity::class.java)
                if(response.isSuccessful){
                    // 정상적으로 통신이 성공된 경우
                    var result: User? = response.body()
                    Log.d("YMC", "onResponse2 성공: " + result.toString());
                    intent.putExtra("Success_Value", 1)

                }else{
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    Log.d("YMC", "onResponse2 실패")
                    intent.putExtra("Success_Value", 0)
                }
                startActivity(intent)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("YMC", "onFailure2 에러: " + t.message.toString());

            }
        })

    }
}

