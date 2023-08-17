package com.sunrin.vacation_project

import com.google.gson.annotations.SerializedName

//ExampleResponse.kt
data class User(
    @SerializedName("Values")
    val uidValues: Int,
    @SerializedName("tembler_num")
    val tembler_num: Int,

    @SerializedName("Success")
    val Success: Boolean

    // @SerializedName으로 일치시켜 주지않을 경우엔 클래스 변수명이 일치해야함
    // @SerializedName()로 변수명을 입치시켜주면 클래스 변수명이 달라도 알아서 매핑
)



