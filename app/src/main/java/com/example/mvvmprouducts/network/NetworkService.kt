package com.example.mvvmprouducts.network

import com.example.mvvmprouducts.model.ProuductResponce
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {
    @GET("products")
    suspend fun getAllProduct(): ProuductResponce
}