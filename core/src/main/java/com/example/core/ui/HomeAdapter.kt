package com.example.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.databinding.GamesItemBinding
import com.example.core.domain.model.Game

class HomeAdapter(
    private val listGames:List<Game>,
    val onClick :(Game) -> Unit
):RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

//    var onSetFavourite: ((Game) -> Unit)?= null

    class ViewHolder(binding: GamesItemBinding):RecyclerView.ViewHolder(binding.root){
        val imageIv = binding.gamesIv
        val gameNameTv = binding.gameNameTv
        val gameScoreTv = binding.scoreTv
//        val favouriteBtn = binding.wishlistIv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GamesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = listGames[position]

        Glide.with(holder.itemView)
            .load(game.backgroundImage)
            .fitCenter()
            .centerCrop()
            .into(holder.imageIv)

        holder.apply {
            gameNameTv.text = game.name
            gameScoreTv.text = StringBuilder("Score: ")
                .append(game.rating.toString())
                .append("/")
                .append(game.ratingTop)
        }

        holder.itemView.setOnClickListener {
            onClick(game)
        }

//        holder.favouriteBtn.setOnClickListener {
//            onSetFavourite?.invoke(game)
//        }
    }

    override fun getItemCount(): Int = listGames.size
}