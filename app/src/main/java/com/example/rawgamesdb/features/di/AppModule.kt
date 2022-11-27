package com.example.rawgamesdb.features.di

import com.example.core.domain.usecase.GameInteractor
import com.example.core.domain.usecase.GameUseCase
import com.example.core.domain.usecase.LoginInteractor
import com.example.core.domain.usecase.LoginUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideLoginUseCase(loginInteractor: LoginInteractor):
            LoginUseCase

    @Binds
    @Singleton
    abstract fun provideGameUseCase(gameInteractor: GameInteractor):
            GameUseCase
}