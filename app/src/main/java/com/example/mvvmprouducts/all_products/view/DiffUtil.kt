package com.example.mvvmprouducts.all_products.view

import androidx.recyclerview.widget.DiffUtil
import com.example.mvvmprouducts.model.Product

class DiffUtil : DiffUtil.ItemCallback<Product>(){
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
       return oldItem == newItem
    }
}