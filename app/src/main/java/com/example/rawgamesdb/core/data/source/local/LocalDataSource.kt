package com.example.rawgamesdb.core.data.source.local

import android.content.Context
import com.example.rawgamesdb.core.data.source.local.entity.GameEntity
import com.example.rawgamesdb.core.data.source.local.room.GameDao
import com.example.rawgamesdb.core.data.source.local.sharedpreferences.LoginPreference
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(
    private val gameDao: GameDao,
    private val prefs: LoginPreference
){
    companion object {
        private var instance: LocalDataSource?= null

        fun getInstance(gameDao: GameDao,context:Context) : LocalDataSource {
            val loginPreference = LoginPreference(context)
            return instance ?: synchronized(this){
                instance ?: LocalDataSource(gameDao, loginPreference)
            }
        }

    }

    fun getAllGame(): Flow<List<GameEntity>> = gameDao.getAllGame()

    fun getAllFavouritedGame(): Flow<List<GameEntity>> = gameDao.getAllFavouritedGame()

    fun updateFavouriteGame(game: GameEntity, isFavourite: Boolean) {
        game.favorite = isFavourite
        gameDao.updateFavouriteGame(game)
    }

    suspend fun insertFavouriteGame(game: GameEntity){
        game.favorite = true
        gameDao.insertFavouriteGame(game)
    }

    fun setLoginToken(token:String) = prefs.setLoginToken(token)

    fun getLoginToken():String? = prefs.getLoginToken()

    fun clearLoginToken() = prefs.clearLoginToken()
}