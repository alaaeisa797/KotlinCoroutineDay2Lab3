package com.example.mvvmprouducts.favourite.view

import com.example.mvvmprouducts.model.Product

interface OnDeleteClickListner {

    fun deleteFromFavourite(p: Product)
}