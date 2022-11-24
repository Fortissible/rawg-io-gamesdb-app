package com.example.rawgamesdb.features.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.rawgamesdb.core.domain.model.Game
import com.example.rawgamesdb.core.domain.usecase.GameUseCase
import kotlinx.coroutines.launch

class MyFavGamesViewModel(
    private val gameUseCase: GameUseCase
):ViewModel() {

    val allFavouritedGame = gameUseCase.getAllFavouritedGame().asLiveData()

    fun updateFavouriteGame(game: Game, isFavourited:Boolean){
        viewModelScope.launch {
            if (isFavourited) gameUseCase.deleteFavouriteGame(game)
            else gameUseCase.insertFavourite(game)
        }
    }
}