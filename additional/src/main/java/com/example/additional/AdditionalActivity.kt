package com.example.additional

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.additional.databinding.ActivityAdditionalBinding
import com.example.core.domain.model.Game

class AdditionalActivity : AppCompatActivity() {

    lateinit var binding : ActivityAdditionalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdditionalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val game = intent.getParcelableExtra<Game>("additional_game")

        binding.apply {
            detailGameNameTv.text = game?.name
            gameAdditionalTv.text = StringBuilder(game?.name.toString())
                .append(" Additional")
                .toString()
            detailPlaytimeValueTv.text = game?.playtime.toString()
            detailMetacriticValueTv.text = game?.metacritic.toString()
            detailRatingsCountValueTv.text = game?.ratingsCount.toString()
            detailReleaseValueTv.text = game?.released.toString()
            detailScoreValueTv.text = StringBuilder(game?.rating.toString())
                .append("/")
                .append(game?.ratingTop.toString())
                .toString()
            detailUpdatedValueTv.text = game?.updated.toString()
            Glide.with(this@AdditionalActivity)
                .load(game?.backgroundImage)
                .fitCenter()
                .centerCrop()
                .into(detailGameIv)
        }
    }
}