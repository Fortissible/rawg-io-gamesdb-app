package com.example.rawgamesdb.features.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.rawgamesdb.core.domain.model.Game
import com.example.rawgamesdb.core.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyFavGamesViewModel @Inject constructor(
    gameUseCase: GameUseCase
):ViewModel() {

    val allFavouritedGame = gameUseCase.getAllFavouritedGame().asLiveData()

    val updateFavouriteGame: (game: Game, isFavourited: Boolean) -> Unit = { game, isFavourited ->
        viewModelScope.launch {
            if (isFavourited) gameUseCase.deleteFavouriteGame(game)
            else gameUseCase.insertFavourite(game)

        }
    }
}