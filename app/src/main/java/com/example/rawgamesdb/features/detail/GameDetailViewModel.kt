package com.example.rawgamesdb.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.data.Resource
import com.example.core.domain.model.Game
import com.example.core.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    gameUseCase: GameUseCase
): ViewModel() {

    val getGameDetail : (id:String, key:String)
    -> LiveData<Resource<Game>> = { id, key ->
       gameUseCase.getGameDetail(id,key).asLiveData()
    }

    val updateFavouriteGame: (game: Game, isFavourited:Boolean) -> Unit = { game,isFavourited ->
        viewModelScope.launch {
            if (isFavourited) gameUseCase.deleteFavouriteGame(game)
            else gameUseCase.insertFavourite(game)
        }
    }
}