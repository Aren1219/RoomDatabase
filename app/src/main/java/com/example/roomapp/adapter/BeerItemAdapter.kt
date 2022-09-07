package com.example.roomapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.roomapp.R
import com.example.roomapp.databinding.ItemBeerBinding
import com.example.roomapp.model.Beers

class BeerItemAdapter (
    val context: Context,
    val beersList: Beers
    ): RecyclerView.Adapter<BeerItemAdapter.BeerViewHolder>(){

    class BeerViewHolder(beerItem: View): RecyclerView.ViewHolder(beerItem) {
        val binding: ItemBeerBinding = ItemBeerBinding.bind(beerItem)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BeerViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_beer,parent,false)
    )

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        Glide.with(context).load(beersList[position].imageUrl).into(holder.binding.ivBeer)
        holder.binding.tvBeerName.text = beersList[position].name
        holder.binding.tvBeerDescription.text = beersList[position].description
    }

    override fun getItemCount(): Int = beersList.size
}