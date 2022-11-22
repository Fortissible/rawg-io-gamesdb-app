package com.example.rawgamesdb.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.rawgamesdb.core.data.Resource
import com.example.rawgamesdb.core.domain.model.Game
import com.example.rawgamesdb.core.domain.usecase.GameUseCase
import com.example.rawgamesdb.core.domain.usecase.LoginUseCase

class HomeViewModel (
    private val gameUseCase: GameUseCase,
    private val loginUseCase: LoginUseCase
): ViewModel() {

    private val _games = MutableLiveData<Resource<List<Game>>>()
    val games : LiveData<Resource<List<Game>>> = _games

    private val _isLogout = MutableLiveData<Boolean>()
    val isLogout : LiveData<Boolean> = _isLogout

    fun getAllGameFromApi(key:String) {
        _games.value = gameUseCase.getAllGameFromApi(key).asLiveData().value
    }

    suspend fun updateFavouriteGame(game:Game,isFavourited:Boolean){
        if (isFavourited) gameUseCase.deleteFavouriteGame(game)
        else gameUseCase.insertFavourite(game)
    }

    fun logout(){
        _isLogout.value = loginUseCase.logoutAccount().asLiveData().value
    }

}