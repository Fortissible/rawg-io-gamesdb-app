package com.example.rawgamesdb.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rawgamesdb.core.di.Injection
import com.example.rawgamesdb.core.domain.usecase.GameUseCase
import com.example.rawgamesdb.core.domain.usecase.LoginUseCase
import com.example.rawgamesdb.features.detail.GameDetailViewModel
import com.example.rawgamesdb.features.favourite.MyFavGamesViewModel
import com.example.rawgamesdb.features.home.HomeViewModel
import com.example.rawgamesdb.features.login.LoginViewModel


class ViewModelFactory private constructor(
    private val gameUseCase: GameUseCase,
    private val loginUseCase: LoginUseCase
)
    :ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance : ViewModelFactory?= null

        fun getInstance(context:Context):ViewModelFactory =
            instance ?: synchronized(this){
                instance?: ViewModelFactory(
                    Injection.provideGameUseCase(context),
                    Injection.provideLoginUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) ->
            {
                return HomeViewModel(gameUseCase,loginUseCase) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                return LoginViewModel(loginUseCase) as T
            }
            modelClass.isAssignableFrom(GameDetailViewModel::class.java) -> {
                return GameDetailViewModel(gameUseCase) as T
            }
            modelClass.isAssignableFrom(MyFavGamesViewModel::class.java) -> {
                return MyFavGamesViewModel(gameUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}