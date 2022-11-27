package com.example.rawgamesdb.features.di

import com.example.core.domain.usecase.GameUseCase
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavModuleDependencies {

    fun gamUseCase():GameUseCase
}