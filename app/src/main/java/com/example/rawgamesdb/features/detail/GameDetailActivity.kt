package com.example.rawgamesdb.features.detail

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.text.HtmlCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.core.data.Resource
import com.example.core.domain.model.Game
import com.example.core.utils.Constant
import com.example.rawgamesdb.R
import com.example.rawgamesdb.databinding.ActivityGameDetailBinding
import com.example.rawgamesdb.features.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class GameDetailActivity : AppCompatActivity() {

    private lateinit var game : Game
    private val gameDetailViewModel : GameDetailViewModel by viewModels()
    private lateinit var binding: ActivityGameDetailBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityGameDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gameId = intent.getIntExtra(HomeFragment.EXTRA_ID,0)

        binding.additionalBtn.setOnClickListener{
            val intent = Intent(
                this,
                Class.forName("com.example.additional.AdditionalActivity")
            )
            intent.putExtra("additional_game",game)
            startActivity(intent)
        }

        gameDetailViewModel.getGameDetail(gameId.toString(), Constant.tokenRAWGamesApi)
            .observe(this){
                if ( it!= null) {
                    when (it) {
                        is Resource.Loading<*> -> {
                            changeUI(isLoading = true, isFavourited = false)
                        }
                        is Resource.Success<*> -> {
                            game = it.data!!

                            setGameDetail(game)
                            if (it.data?.favorite == true) {
                                changeUI(isLoading = false, isFavourited = true)
                                binding.wishlistBtn.setOnClickListener { _ ->
                                    val gameDeletedText =
                                        StringBuilder(
                                            "Deleted ${game.name} from favourite list")
                                            .toString()
                                    clickAction(
                                        gameDeletedText,game
                                    )
                                }

                            } else {
                                changeUI(isLoading = false, isFavourited = false)
                                binding.wishlistBtn.setOnClickListener { _ ->
                                    val gameAddedText =
                                        StringBuilder(
                                            "Added ${game.name} to favourite list")
                                            .toString()
                                    clickAction(
                                        gameAddedText, game
                                    )
                                }
                            }
                        }
                        is Resource.Error<*> -> {
                            changeUI(isLoading = false, isFavourited = false)
                        }
                    }
                }
            }
    }

    private fun changeUI(isLoading:Boolean,isFavourited:Boolean){
        if (isLoading) {
            binding.loadingDetail.visibility = View.VISIBLE
            binding.yourGameIsLoadingTv.visibility = View.VISIBLE
            binding.contentContainer.visibility = View.INVISIBLE
        } else {
            binding.loadingDetail.visibility = View.GONE
            binding.yourGameIsLoadingTv.visibility = View.GONE
            binding.contentContainer.visibility = View.VISIBLE
            if (isFavourited) {
                binding.wishlistBtn.text = StringBuilder("Delete from wishlist")
                    .toString()
                binding.wishlistBtn.backgroundTintList =
                    AppCompatResources.getColorStateList(
                        this,R.color.steam_text_primary
                    )
            }
        }
    }

    private fun clickAction(text:String,game: Game){
        lifecycleScope.launch {
            gameDetailViewModel.updateFavouriteGame(game,game.favorite)
        }
        Toast.makeText(
            this,
            text,
            Toast.LENGTH_SHORT).show()
        onBackPressed()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setGameDetail(game: Game?){
        if (game?.id != null){
            val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
            val formattedDate = LocalDate.parse(game.released).format(formatter)
            Glide.with(binding.root)
                .load(game.backgroundImage)
                .fitCenter()
                .centerCrop()
                .into(binding.detailGameIv)
            binding.apply {
                detailGameNameTv.text = game.name
                detailReleaseValueTv.text = formattedDate
                detailScoreValueTv.text = String.format(
                    getString(R.string.score_value_text),
                    game.rating.toString()
                )
                detailDescTv.text = HtmlCompat.fromHtml(
                    String.format(getString(R.string.desc_html_format_text), game.description),
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )
            }
        } else {
            Log.d("DATA KOSONG", "setGameDetail: ")
        }
    }
}