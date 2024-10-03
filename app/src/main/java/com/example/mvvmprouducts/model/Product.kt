package com.example.mvvmprouducts.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "MyProuducts")
data class Product (@PrimaryKey var id:Int,var title: String, var description: String, var thumbnail: String)
    :Serializable

