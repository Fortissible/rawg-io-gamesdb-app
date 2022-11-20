package com.example.rawgamesdb.core.data.source.local

import androidx.lifecycle.LiveData
import com.example.rawgamesdb.core.data.source.local.entity.GameEntity
import com.example.rawgamesdb.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(
    private val gameDao: GameDao
){
    companion object {
        private var instance: LocalDataSource?= null

        fun getInstance(gameDao: GameDao) : LocalDataSource =
            instance ?: synchronized(this){
                instance ?: LocalDataSource(gameDao)
            }
    }

    fun getAllGame(): Flow<List<GameEntity>> = gameDao.getAllGame()

    fun getAllFavouritedGame(): Flow<List<GameEntity>> = gameDao.getAllFavouritedGame()

    suspend fun insertAllGame(games: List<GameEntity>) = gameDao.insertAllGame(games)

    fun updateFavouriteGame(game: GameEntity, isFavourite: Boolean) {
        game.favorite = isFavourite
        gameDao.updateFavouriteGame(game)
    }
}