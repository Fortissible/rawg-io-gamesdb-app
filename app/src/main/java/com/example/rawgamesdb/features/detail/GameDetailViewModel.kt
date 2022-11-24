package com.example.rawgamesdb.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.rawgamesdb.core.data.Resource
import com.example.rawgamesdb.core.domain.model.Game
import com.example.rawgamesdb.core.domain.model.GameDetail
import com.example.rawgamesdb.core.domain.usecase.GameUseCase

class GameDetailViewModel (
    private val gameUseCase: GameUseCase
): ViewModel() {

    val getGameDetailFromApi : (id:String, key:String)
    -> LiveData<Resource<GameDetail>> = { id,key ->
       gameUseCase.getGameDetailFromApi(id,key).asLiveData()
    }

    suspend fun updateFavouriteGame(game:GameDetail,isFavourited:Boolean){
        if (isFavourited) gameUseCase.deleteFavouriteGame(game)
        else gameUseCase.insertFavourite(game)
    }
}