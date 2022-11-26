package com.example.core.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.core.data.source.local.room.GameDao
import com.example.core.data.source.local.room.GameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context:Context):GameDatabase = Room.databaseBuilder(
        context,
        GameDatabase::class.java,
        "Game.db"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideGameDao(gameDatabase:GameDatabase):GameDao = gameDatabase.gameDao()

    @Provides
    fun providePreferences(@ApplicationContext context:Context):SharedPreferences =
        context.getSharedPreferences("login_prefs",Context.MODE_PRIVATE)

}