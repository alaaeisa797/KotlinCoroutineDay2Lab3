package com.example.mvvmprouducts.network

import com.example.mvvmprouducts.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRemoteDataScource private constructor( private val networkService: NetworkService)
{

suspend fun getAllProudutOnline () : Flow<List<Product>?>
{


    return flow {
        val result =  networkService.getAllProduct().products
        emit(result)
    }
}


    companion object {

        private var instance : ProductRemoteDataScource? = null

        fun getInstance( networkService: NetworkService) : ProductRemoteDataScource
        {
            return instance ?: synchronized(this){
                val result = ProductRemoteDataScource(networkService)
                instance=result
                return result
            }


        }


    }



}