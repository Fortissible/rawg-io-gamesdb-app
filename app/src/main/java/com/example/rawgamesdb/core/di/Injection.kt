package com.example.rawgamesdb.core.di

import android.content.Context
import com.example.rawgamesdb.core.data.GameRepository
import com.example.rawgamesdb.core.data.LoginRepository
import com.example.rawgamesdb.core.data.source.local.LocalDataSource
import com.example.rawgamesdb.core.data.source.local.room.GameDatabase
import com.example.rawgamesdb.core.data.source.remote.RemoteDataSource
import com.example.rawgamesdb.core.data.source.remote.network.ApiConfig
import com.example.rawgamesdb.core.domain.repository.IGameRepository
import com.example.rawgamesdb.core.domain.repository.ILoginRepository
import com.example.rawgamesdb.core.domain.usecase.GameInteractor
import com.example.rawgamesdb.core.domain.usecase.GameUseCase
import com.example.rawgamesdb.core.domain.usecase.LoginInteractor
import com.example.rawgamesdb.core.domain.usecase.LoginUseCase

object Injection {
    private fun provideGameRepository(context:Context): IGameRepository{
        val database = GameDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideRAWGamesApiService())
        val localDataSource = LocalDataSource.getInstance(database.gameDao(),context)

        return GameRepository.getInstance(remoteDataSource,localDataSource)
    }

    private fun provideLoginRepository(context:Context): ILoginRepository{
        val database = GameDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideReqresApiService())
        val localDataSource = LocalDataSource.getInstance(database.gameDao(),context)

        return LoginRepository.getInstance(remoteDataSource,localDataSource)
    }

    fun provideGameUseCase(context:Context):GameUseCase{
        val repository = provideGameRepository(context)
        return GameInteractor(repository)
    }

    fun provideLoginUseCase(context:Context):LoginUseCase{
        val repository = provideLoginRepository(context)
        return LoginInteractor(repository)
    }
}