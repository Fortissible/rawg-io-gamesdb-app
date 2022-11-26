package com.example.rawgamesdb.features.di

import com.example.rawgamesdb.core.domain.usecase.GameInteractor
import com.example.rawgamesdb.core.domain.usecase.GameUseCase
import com.example.rawgamesdb.core.domain.usecase.LoginInteractor
import com.example.rawgamesdb.core.domain.usecase.LoginUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun provideLoginUseCase(loginInteractor: LoginInteractor):
            LoginUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideGameUseCase(gameInteractor: GameInteractor):
            GameUseCase
}