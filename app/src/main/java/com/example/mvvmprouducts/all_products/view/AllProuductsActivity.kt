package com.example.mvvmprouducts.all_products.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmprouducts.ApiState
import com.example.mvvmprouducts.all_products.view_model.AllProdctsViewModelFactory
import com.example.mvvmprouducts.all_products.view_model.AllProuductViewModel
import com.example.mvvmprouducts.databinding.ActivityAllProuductsBinding
import com.example.mvvmprouducts.db.ProductLocalDataSource
import com.example.mvvmprouducts.db.RoomDataBase
import com.example.mvvmprouducts.model.Product
import com.example.mvvmprouducts.model.Reposatory
import com.example.mvvmprouducts.network.NetworkService
import com.example.mvvmprouducts.network.ProductRemoteDataScource
import com.example.mvvmprouducts.network.RetrofitHelper
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AllProuductsActivity : AppCompatActivity(), OnFavClickListner {
    lateinit var binding: ActivityAllProuductsBinding
    lateinit var allProuductAdapter: AllProuductAdapter
    lateinit var viewModel: AllProuductViewModel
    lateinit var vmFactory: AllProdctsViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //initialize the view model object
        vmFactory = AllProdctsViewModelFactory(
            Reposatory.getInstance(
                ProductLocalDataSource(RoomDataBase.getInstance(this).getAllProuduct()),
                ProductRemoteDataScource.getInstance(
                    RetrofitHelper.retrofitInstance.create(
                        NetworkService::class.java
                    )
                )
            )
        )

        viewModel = ViewModelProvider(this, vmFactory).get(AllProuductViewModel::class.java)

        viewModel.getAllProduct()
        binding = ActivityAllProuductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        allProuductAdapter = AllProuductAdapter(this, this) // da lessa hya5od cotext 3shan el glide
        binding.rvAllProuducts.apply {
            layoutManager = LinearLayoutManager(this@AllProuductsActivity)
            adapter = allProuductAdapter
        }
//
//        lifecycleScope.launch(Dispatchers.IO) {
////            val retrofit = RetrofitHelper.retrofitInstance.create(NetworkService::class.java)
////            val response = retrofit.getAllProduct()
////            val result = response.body()?.products
////            withContext(Dispatchers.Main)
////            {
////                allProuductAdapter.submitList(result?.toMutableList())
////            }
//
//            val repo = Reposatory
//            val response  = repo.retrofit.networkService.getAllProduct()
//            val result = response.body()?.products
//            withContext(Dispatchers.Main)
//            {
//                allProuductAdapter.submitList(result?.toMutableList())
//            }
//
//        }
        lifecycleScope.launch {


            viewModel.liveData.collectLatest { result ->
                when (result) {
                    is ApiState.Loading -> {}
                    is ApiState.Success -> {
                        allProuductAdapter.submitList(result.data)
                    }

                    else -> {
                        Log.d("TAG", "onCreate: failure")
                    }
                }

            }


        }
    }

    override fun addToFacourite(p: Product) {
        viewModel.insert(p)
    }
}