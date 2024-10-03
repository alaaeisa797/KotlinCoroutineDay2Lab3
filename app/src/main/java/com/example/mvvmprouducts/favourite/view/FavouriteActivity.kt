package com.example.mvvmprouducts.favourite.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmprouducts.R
import com.example.mvvmprouducts.all_products.view_model.AllProdctsViewModelFactory
import com.example.mvvmprouducts.all_products.view_model.AllProuductViewModel
import com.example.mvvmprouducts.databinding.ActivityFavouriteBinding
import com.example.mvvmprouducts.db.ProductLocalDataSource
import com.example.mvvmprouducts.db.RoomDataBase
import com.example.mvvmprouducts.model.Product
import com.example.mvvmprouducts.model.Reposatory
import com.example.mvvmprouducts.network.NetworkService
import com.example.mvvmprouducts.network.ProductRemoteDataScource
import com.example.mvvmprouducts.network.RetrofitHelper

class FavouriteActivity : AppCompatActivity() , OnDeleteClickListner {
    lateinit var binding: ActivityFavouriteBinding
    lateinit var viewModel : AllProuductViewModel
    lateinit var vmFactory : AllProdctsViewModelFactory
    lateinit var favouriteProuudctAdapter: FavouriteProuudctAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding=ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        favouriteProuudctAdapter = FavouriteProuudctAdapter(this,this)
        binding.rvFavouriteRecycleView.apply {
            layoutManager = LinearLayoutManager(this@FavouriteActivity)
           adapter =  favouriteProuudctAdapter

        }
        vmFactory = AllProdctsViewModelFactory(
            Reposatory.getInstance(
                ProductLocalDataSource(RoomDataBase.getInstance(this).getAllProuduct()),
            ProductRemoteDataScource.getInstance(
                RetrofitHelper.retrofitInstance.create(
                    NetworkService::class.java))))

        viewModel = ViewModelProvider(this ,vmFactory ).get(AllProuductViewModel::class.java)

        viewModel.getFavProuduct()

        viewModel.myFAVLivaData.observe(this){
            favouriteProuudctAdapter.submitList(it)
        }

    }

    override fun deleteFromFavourite(p: Product) {
        viewModel.delete(p)
    }
}