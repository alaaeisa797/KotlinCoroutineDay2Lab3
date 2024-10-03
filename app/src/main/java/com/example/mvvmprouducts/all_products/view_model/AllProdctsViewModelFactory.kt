package com.example.mvvmprouducts.all_products.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmprouducts.model.Reposatory

class AllProdctsViewModelFactory (private val repo : Reposatory) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AllProuductViewModel::class.java))
        {
            AllProuductViewModel(repo) as T
        }
        else
        {
            throw IllegalArgumentException(" class view  model not found ")
        }
    }
}