package com.example.rawgamesdb.features.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.rawgamesdb.R
import com.example.rawgamesdb.databinding.FragmentGameDetailBinding

class GameDetailFragment : Fragment() {

    private var _binding : FragmentGameDetailBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.wishlistBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_gameDetailFragment_to_myFavGamesFragment)
        }
    }
}