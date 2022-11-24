package com.example.rawgamesdb.features.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rawgamesdb.R
import com.example.rawgamesdb.core.data.Resource
import com.example.rawgamesdb.core.domain.model.Game
import com.example.rawgamesdb.core.ui.HomeAdapter
import com.example.rawgamesdb.core.ui.ViewModelFactory
import com.example.rawgamesdb.core.utils.Constant
import com.example.rawgamesdb.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel : HomeViewModel by viewModels {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gamesRv.setHasFixedSize(true)

        binding.gotoFavActionBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_myFavGamesFragment)
        }

        binding.logoutBtn.setOnClickListener {
            homeViewModel.logout
            view.findNavController().popBackStack(R.id.action_homeFragment_to_loginFragment,false)
        }

        homeViewModel.getAllGameFromApi(Constant.tokenRAWGamesApi).observe(viewLifecycleOwner){
            if ( it!= null) {
                when (it) {
                    is Resource.Loading -> {
                        showLoading(true)
                        Log.d("LOADING LIST GAME GAN", "onViewCreated: ")
                    }
                    is Resource.Success -> {
                        showLoading(false)
                        Log.d("SUKSES LIST GAME GAN", "onViewCreated: ${it.data}")
                        setRvData(view,it.data)
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        Log.d("ERROR LIST GAME GAN", "onViewCreated: ")
                    }
                }
            }
        }
    }

    private fun setRvData(view:View,gameList: List<Game>?){
        binding.gamesRv.layoutManager = GridLayoutManager(requireActivity(),2)
        if (!gameList.isNullOrEmpty()){
            val rvAdapter = HomeAdapter(gameList) {
                val mBundle = Bundle()
                mBundle.putInt(EXTRA_ID, it.id!!)
                view.findNavController().navigate(R.id.action_homeFragment_to_gameDetailFragment,mBundle)
            }
//            rvAdapter.onSetFavourite = {
//                lifecycleScope.launch {
//                    homeViewModel.updateFavouriteGame(it,it.favorite)
//                }
//            }
            binding.gamesRv.adapter = rvAdapter
        }
    }

    private fun showLoading(isLoading:Boolean){
        binding.apply {
            if (isLoading) {
                loadingGameList.visibility = View.VISIBLE
                yourGameIsLoadingTv.visibility = View.VISIBLE
                contentContainer.visibility = View.INVISIBLE
            } else {
                loadingGameList.visibility = View.GONE
                yourGameIsLoadingTv.visibility = View.GONE
                contentContainer.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}