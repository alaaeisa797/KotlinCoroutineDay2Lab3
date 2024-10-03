package com.example.mvvmprouducts.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmprouducts.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface DAO {

    @Query("SELECT * FROM  MyProuducts")
     fun getAllMyProuduct  () : Flow<List<Product>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert  (product: Product)

    @Delete
    suspend fun delete (product: Product)

}