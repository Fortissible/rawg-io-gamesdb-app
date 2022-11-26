package com.example.rawgamesdb.features.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.ui.FavGamesAdapter
import com.example.rawgamesdb.databinding.FragmentMyFavGamesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFavGamesFragment : Fragment() {

    private var _binding : FragmentMyFavGamesBinding ?= null
    private val binding get() = _binding!!
    private val myFavGamesViewModel : MyFavGamesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMyFavGamesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gamesRv.setHasFixedSize(true)
        myFavGamesViewModel.allFavouritedGame.observe(viewLifecycleOwner){
            binding.gamesRv.layoutManager = LinearLayoutManager(requireActivity())
            val adapter = FavGamesAdapter(it){ game ->
                myFavGamesViewModel.updateFavouriteGame(game,true)
                Toast.makeText(
                    requireActivity(),
                    "Successfuly delete ${game.name} from favourite list",
                    Toast.LENGTH_SHORT).show()
            }
            binding.gamesRv.adapter = adapter
        }
    }
}