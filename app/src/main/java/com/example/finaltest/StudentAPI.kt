package com.example.finaltest

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface StudentAPI {
    @GET("alluser")
    fun retrieveStudent(): Call<List<Student>>
    @FormUrlEncoded
    @POST("user")
    fun insertStd(
        @Field("username") username :String,
        @Field("Internet Packet") internet :String,
        @Field("Month") month :Int,
        @Field("totalprice") price :Int
    ): Call<Student>
}