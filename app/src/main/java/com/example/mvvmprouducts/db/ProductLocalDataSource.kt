package com.example.mvvmprouducts.db

import com.example.mvvmprouducts.model.Product
import kotlinx.coroutines.flow.Flow

class ProductLocalDataSource (val dao : DAO) {

    suspend fun insert (product :Product)
    {
        dao.insert(product)
    }

    suspend fun delete (product :Product)
    {
        dao.delete(product)
    }
     fun getAllFavouriteProduct () : Flow<List<Product>>
    {
        return  dao.getAllMyProuduct()
    }
}