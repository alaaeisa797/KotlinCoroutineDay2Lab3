package com.example.mvvmprouducts

import com.example.mvvmprouducts.model.Product

sealed class ApiState {
    data class Success(val data: List<Product>) : ApiState()
    data class Failure(val message: String) : ApiState()
    object Loading : ApiState()
}