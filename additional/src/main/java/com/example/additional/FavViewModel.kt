package com.example.additional

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.Game
import com.example.core.domain.usecase.GameUseCase
import kotlinx.coroutines.launch

class FavViewModel(gameUseCase: GameUseCase):ViewModel() {
    val favouritedGame = gameUseCase.getAllFavouritedGame().asLiveData()

    val updateFavouriteGame: (game: Game, isFavourited: Boolean) -> Unit = { game, isFavourited ->
        viewModelScope.launch {
            if (isFavourited) gameUseCase.deleteFavouriteGame(game)
            else gameUseCase.insertFavourite(game)
        }
    }
}