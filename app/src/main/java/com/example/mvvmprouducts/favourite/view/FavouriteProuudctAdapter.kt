package com.example.mvvmprouducts.favourite.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmprouducts.all_products.view.AllProuductAdapter
import com.example.mvvmprouducts.all_products.view.DiffUtil
import com.example.mvvmprouducts.databinding.ActivityFavouriteBinding
import com.example.mvvmprouducts.databinding.FavouriteItemBinding
import com.example.mvvmprouducts.databinding.ProductItemBinding
import com.example.mvvmprouducts.model.Product

class FavouriteProuudctAdapter (var context: Context , var onDeleteClickListner: OnDeleteClickListner): ListAdapter<Product, FavouriteProuudctAdapter.FavouriteProuductViewHolder>( DiffUtil()){
    lateinit var binding: FavouriteItemBinding





    class FavouriteProuductViewHolder (var binding:FavouriteItemBinding) : RecyclerView.ViewHolder(binding.root)
    {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteProuductViewHolder {
        val layoutInflator = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = FavouriteItemBinding.inflate(layoutInflator,parent, false)
        return FavouriteProuudctAdapter.FavouriteProuductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteProuductViewHolder, position: Int) {
        var currentProuduct : Product = getItem(position)
        holder.binding.tvTitle.text = currentProuduct.title
        holder.binding.tvDesc.text = currentProuduct.description
        Glide.with(context).load(currentProuduct.thumbnail).into(holder.binding.ivProductImage)

        holder.binding.btnDelete .setOnClickListener(){
            onDeleteClickListner.deleteFromFavourite(currentProuduct)

        }
    }
}