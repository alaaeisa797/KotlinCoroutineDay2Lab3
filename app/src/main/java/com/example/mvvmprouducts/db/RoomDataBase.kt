package com.example.mvvmprouducts.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmprouducts.model.Product
import com.example.mvvmprouducts.network.NetworkService
import com.example.mvvmprouducts.network.ProductRemoteDataScource

@Database(entities = arrayOf(Product::class), version = 1)
abstract class RoomDataBase : RoomDatabase() {

    abstract fun getAllProuduct(): DAO

    companion object {

        private var instance: RoomDataBase? = null

        fun getInstance(context: Context): RoomDataBase {
            return RoomDataBase.instance ?: synchronized(this) {

                val db = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDataBase::class.java,
                    "MyDataBase"
                ).build()
                instance = db
                db
            }


        }

    }
}