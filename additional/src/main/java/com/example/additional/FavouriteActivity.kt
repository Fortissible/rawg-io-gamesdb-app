package com.example.additional

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.additional.databinding.ActivityFavouriteBinding
import com.example.core.domain.model.Game
import com.example.core.ui.FavGamesAdapter
import com.example.rawgamesdb.features.di.FavModuleDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavouriteActivity : AppCompatActivity() {

    @Inject
    lateinit var factory : ViewModelFactory

    private val favViewModel : FavViewModel by viewModels {
        factory
    }

    private lateinit var binding : ActivityFavouriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        DaggerFavComponent.builder()
            .context(this)
            .appDeps(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val removeFavourite : (game: Game) -> Unit = { game ->
            favViewModel.updateFavouriteGame(game,true)
            Toast.makeText(
                this,
                "Successfuly delete ${game.name} from favourite list",
                Toast.LENGTH_SHORT).show()
        }

        val goToDetail : (game: Game) -> Unit = { game ->
            val intent = Intent(
                this,
                Class.forName("com.example.rawgamesdb.features.detail.GameDetailActivity")
            )
            intent.putExtra("extra_id",game.id)
            startActivity(intent)
        }

        favViewModel.favouritedGame.observe(this){
            binding.gamesRv.layoutManager = LinearLayoutManager(this)
            val adapter = FavGamesAdapter(it, removeFavourite = removeFavourite, moveToDetail = goToDetail)
            binding.gamesRv.adapter = adapter
        }
    }
}