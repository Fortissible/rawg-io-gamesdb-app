package com.example.rawgamesdb.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.rawgamesdb.core.data.Resource
import com.example.rawgamesdb.core.domain.model.Game
import com.example.rawgamesdb.core.domain.usecase.GameUseCase
import com.example.rawgamesdb.core.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    gameUseCase: GameUseCase,
    loginUseCase: LoginUseCase
): ViewModel() {

    val getAllGameFromApi:(key:String) -> LiveData<Resource<List<Game>>> = { key ->
        gameUseCase.getAllGameFromApi(key).asLiveData()
    }

    val logout = loginUseCase.logoutAccount()

}