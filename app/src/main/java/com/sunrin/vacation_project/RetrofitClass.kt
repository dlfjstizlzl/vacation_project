package com.sunrin.vacation_project

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClass {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.35.27/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(RetrofitService::class.java)

    fun getInstance(): RetrofitService? {
        return api
    }
}