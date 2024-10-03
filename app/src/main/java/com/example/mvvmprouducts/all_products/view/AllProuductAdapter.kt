package com.example.mvvmprouducts.all_products.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmprouducts.model.Product
import com.example.mvvmprouducts.databinding.ProductItemBinding
import kotlin.contracts.contract

class AllProuductAdapter ( var context: Context , var onFavClickListner :OnFavClickListner ) : ListAdapter<Product, AllProuductAdapter.AllProuductViewHolder>( DiffUtil()) {

    lateinit var binding:ProductItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllProuductViewHolder {
        val layoutInflator = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ProductItemBinding.inflate(layoutInflator,parent, false)
        return AllProuductViewHolder(binding)
    }


    override fun onBindViewHolder(holder: AllProuductViewHolder, position: Int) {
        var currentProuduct : Product = getItem(position)
        holder.binding.tvTitle.text = currentProuduct.title
        holder.binding.tvDesc.text = currentProuduct.description
        Glide.with(context).load(currentProuduct.thumbnail).into(holder.binding.ivProductImage)

        holder.binding.btnAddToFavourites.setOnClickListener(){

            onFavClickListner.addToFacourite(currentProuduct)
        }

    }
    class AllProuductViewHolder (var binding:ProductItemBinding) : RecyclerView.ViewHolder(binding.root)
    {}
}