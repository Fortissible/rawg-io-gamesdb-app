package com.example.rawgamesdb.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.rawgamesdb.core.data.Resource
import com.example.rawgamesdb.core.domain.model.Game
import com.example.rawgamesdb.core.domain.usecase.GameUseCase

class GameDetailViewModel (
    private val gameUseCase: GameUseCase
): ViewModel() {

    val getGameDetail : (id:String, key:String)
    -> LiveData<Resource<Game>> = { id,key ->
       gameUseCase.getGameDetail(id,key).asLiveData()
    }

    suspend fun updateFavouriteGame(game: Game, isFavourited:Boolean){
        if (isFavourited) gameUseCase.deleteFavouriteGame(game)
        else gameUseCase.insertFavourite(game)
    }
}