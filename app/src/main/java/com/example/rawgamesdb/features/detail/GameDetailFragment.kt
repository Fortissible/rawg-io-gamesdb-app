package com.example.rawgamesdb.features.detail

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.rawgamesdb.R
import com.example.rawgamesdb.core.data.Resource
import com.example.rawgamesdb.core.domain.model.GameDetail
import com.example.rawgamesdb.core.ui.ViewModelFactory
import com.example.rawgamesdb.core.utils.Constant
import com.example.rawgamesdb.databinding.FragmentGameDetailBinding
import com.example.rawgamesdb.features.home.HomeFragment
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GameDetailFragment : Fragment() {

    private var _binding : FragmentGameDetailBinding ?= null
    private val binding get() = _binding!!

    private val gameDetailViewModel : GameDetailViewModel by viewModels {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameId = arguments?.getInt(HomeFragment.EXTRA_ID) ?: 0

        gameDetailViewModel.getGameDetailFromApi(gameId.toString(),Constant.tokenRAWGamesApi)
            .observe(viewLifecycleOwner){
                if ( it!= null) {
                    when (it) {
                        is Resource.Loading -> {
                            Log.d("LOADING DETAIL GAN", "onViewCreated: ")
                            binding.contentContainer.visibility = View.INVISIBLE
                            binding.loadingDetail.visibility = View.VISIBLE
                            binding.yourGameIsLoadingTv.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            Log.d("SUKSES DETAIL GAN", "onViewCreated: ${it.data}")
                            binding.loadingDetail.visibility = View.GONE
                            binding.yourGameIsLoadingTv.visibility = View.GONE
                            binding.contentContainer.visibility = View.VISIBLE
                            setGameDetail(it.data)
                            binding.wishlistBtn.setOnClickListener { view ->
                                lifecycleScope.launch {
                                    gameDetailViewModel.updateFavouriteGame(it.data!!,it.data.favorite)
                                }
                                view.findNavController().navigate(R.id.action_gameDetailFragment_to_myFavGamesFragment)
                            }
                        }
                        is Resource.Error -> {
                            binding.contentContainer.visibility = View.INVISIBLE
                            binding.loadingDetail.visibility = View.GONE
                            binding.yourGameIsLoadingTv.visibility = View.GONE
                            Log.d("ERROR DETAIL GAN", "onViewCreated: ")
                        }
                    }
                }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setGameDetail(game: GameDetail?){
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