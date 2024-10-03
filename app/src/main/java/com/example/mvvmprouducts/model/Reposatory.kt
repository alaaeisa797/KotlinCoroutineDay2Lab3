package com.example.mvvmprouducts.model

import com.example.mvvmprouducts.db.ProductLocalDataSource
import com.example.mvvmprouducts.network.ProductRemoteDataScource
import com.example.mvvmprouducts.network.RetrofitHelper
import kotlinx.coroutines.flow.Flow

class  Reposatory private constructor(private val localDataSource: ProductLocalDataSource ,private val remoteDataScource: ProductRemoteDataScource) {

companion object {

    private var instance : Reposatory? = null

    fun getInstance (localDataSource: ProductLocalDataSource , remoteDataScource: ProductRemoteDataScource ) : Reposatory
    {
        return instance?: synchronized(this){
            val repo = Reposatory(localDataSource,remoteDataScource)
           instance=repo
            repo
        }
    }
}

    suspend fun getAllProductsOnline () : Flow<List<Product>?>
    {
       return remoteDataScource.getAllProudutOnline()
    }

    suspend fun insertToFavourie (p : Product)
    {
        return localDataSource.insert(p)
    }

    suspend fun deleteFromFavourie (p : Product)
    {
        return localDataSource.delete(p)
    }
     fun getAllFavouriteProduct (): Flow<List<Product>?>
    {
        return localDataSource.getAllFavouriteProduct()
    }

}