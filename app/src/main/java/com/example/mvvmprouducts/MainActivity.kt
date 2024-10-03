package com.example.mvvmprouducts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmprouducts.all_products.view.AllProuductsActivity
import com.example.mvvmprouducts.databinding.ActivityMainBinding
import com.example.mvvmprouducts.favourite.view.FavouriteActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("TAG", "onCreate: ")
        binding.btnAllProducts.setOnClickListener(){

           val intent = Intent(this , AllProuductsActivity::class.java)
            startActivity(intent)
        }

        binding.btnFavouriteProuducts .setOnClickListener(){
            val intent = Intent(this , FavouriteActivity::class.java)
            startActivity(intent)
        }
        binding.btnExite.setOnClickListener(){

        }
    }
}