package com.example.mvvmprouducts.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object  RetrofitHelper {

    const val URL = "https://dummyjson.com/"
    val retrofitInstance = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


}