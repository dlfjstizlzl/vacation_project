package com.sunrin.vacation_project

import com.google.firebase.firestore.auth.User
import retrofit2.Call
import retrofit2.http.*
interface RetrofitService {

    @GET("uid/")
    fun getUserPage(@Query("uidValue") uidValue: Int): Call<User>
}