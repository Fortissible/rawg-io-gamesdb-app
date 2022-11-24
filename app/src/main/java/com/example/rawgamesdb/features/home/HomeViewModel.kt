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

    val getAllGameFromApi:(key:String) -> LiveData<Resource<List<Game>>> = { key ->
        gameUseCase.getAllGameFromApi(key).asLiveData()
    }

//    suspend fun updateFavouriteGame(game:Game,isFavourited:Boolean){
//        if (isFavourited) gameUseCase.deleteFavouriteGame(game)
//        else gameUseCase.insertFavourite(game)
//    }

    val logout = loginUseCase.logoutAccount()

    val getToken = loginUseCase.getLoginToken().asLiveData()

}