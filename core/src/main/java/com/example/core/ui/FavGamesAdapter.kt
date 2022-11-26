package com.example.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.databinding.FavouriteItemBinding
import com.example.core.domain.model.Game

class FavGamesAdapter(
    private val gameList:List<Game>,
    val removeFavourite:(Game) -> Unit
):RecyclerView.Adapter<FavGamesAdapter.ViewHolder>(){

    class ViewHolder(binding:FavouriteItemBinding):RecyclerView.ViewHolder(binding.root){
        val gameIv = binding.favGamesIv
        val gameNameTv = binding.favGameNameTv
        val gameScoreTv = binding.favScoreTv
        val gameFavIv = binding.wishlistIv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FavouriteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = gameList[position]

        Glide.with(holder.itemView)
            .load(game.backgroundImage)
            .centerCrop()
            .into(holder.gameIv)

        holder.gameScoreTv.text = StringBuilder("Score: ")
            .append(game.rating.toString())
            .append("/")
            .append(game.ratingTop)

        holder.gameNameTv.text = game.name

        holder.gameFavIv.setOnClickListener {
            removeFavourite(game)
        }
    }

    override fun getItemCount(): Int = gameList.size
}