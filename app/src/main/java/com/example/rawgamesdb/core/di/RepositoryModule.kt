package com.example.rawgamesdb.core.di

import com.example.rawgamesdb.core.data.GameRepository
import com.example.rawgamesdb.core.data.LoginRepository
import com.example.rawgamesdb.core.domain.repository.IGameRepository
import com.example.rawgamesdb.core.domain.repository.ILoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideGameRepository(gameRepository: GameRepository)
            :IGameRepository

    @Binds
    abstract fun provideLoginRepository(loginRepository: LoginRepository)
            :ILoginRepository
}