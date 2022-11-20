package com.example.rawgamesdb.core.data.source.local.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class GameDatabase: RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {
        @Volatile
        private var INSTANCE: GameDatabase ?= null

        fun getInstance(context: Context): GameDatabase =
            INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    GameDatabase::class.java,
                    "Game.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }


}