package com.example.rawgamesdb.features.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.rawgamesdb.core.domain.model.Game
import com.example.rawgamesdb.core.domain.usecase.GameUseCase

class MyFavGamesViewModel(
    private val gameUseCase: GameUseCase
):ViewModel() {

    val allFavouritedGame = gameUseCase.getAllFavouritedGame().asLiveData()

    suspend fun updateFavouriteGame(game: Game, isFavourited:Boolean){
        if (isFavourited) gameUseCase.deleteFavouriteGame(game)
        else gameUseCase.insertFavourite(game)
    }
}