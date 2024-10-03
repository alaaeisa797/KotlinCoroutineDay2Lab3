package com.example.mvvmprouducts.all_products.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmprouducts.ApiState

import com.example.mvvmprouducts.model.Product
import com.example.mvvmprouducts.model.Reposatory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllProuductViewModel( private val  repo : Reposatory) : ViewModel() {

//    private var mutapleListLiveData =MutableLiveData<List<Product>>()
//    var myLivaData:LiveData<List<Product>> = mutapleListLiveData

    private val mutableLiveData = MutableStateFlow<ApiState>(ApiState.Loading)
    val liveData = mutableLiveData.asStateFlow()

    private var mutapleFAVListLiveData =MutableLiveData<List<Product>>()
    var myFAVLivaData:LiveData<List<Product>> = mutapleFAVListLiveData

    fun getAllProduct()
    {
        viewModelScope.launch(Dispatchers.IO){
            repo.getAllProductsOnline().catch {e->

                mutableLiveData.emit(ApiState.Failure(e.toString()))
            }.collect {
                mutableLiveData.emit(ApiState.Success(it!!))

            }

        }
    }

    fun insert (p : Product)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertToFavourie(p)
        }
    }
    fun getFavProuduct ()
    {
        viewModelScope.launch(Dispatchers.IO){
            repo.getAllFavouriteProduct().collect{
                mutapleFAVListLiveData .postValue(it)
            }
        }
    }
    fun delete (p :Product)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteFromFavourie(p)
            getFavProuduct()
        }
        }
}