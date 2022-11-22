package com.example.rawgamesdb.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.rawgamesdb.core.data.Resource
import com.example.rawgamesdb.core.domain.model.Game
import com.example.rawgamesdb.core.domain.usecase.GameUseCase

class GameDetailViewModel (
    private val gameUseCase: GameUseCase
): ViewModel() {

    private val _gameDetail = MutableLiveData<Resource<Game>>()
    val gameDetail : LiveData<Resource<Game>> = _gameDetail

    fun getGameDetailFromApi(id:String, key:String) {
        _gameDetail.value = gameUseCase.getGameDetailFromApi(id,key).asLiveData().value
    }

    suspend fun updateFavouriteGame(game:Game,isFavourited:Boolean){
        if (isFavourited) gameUseCase.deleteFavouriteGame(game)
        else gameUseCase.insertFavourite(game)
    }
}