package com.example.finaltest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Student (
    @Expose
    @SerializedName("username") val username: String,

    @Expose
    @SerializedName("Internet Packet") val  internet: String,

    @Expose
    @SerializedName("Month") val month:Int,
    @Expose
    @SerializedName("totalprice") val price:Int){}