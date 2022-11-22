package com.example.rawgamesdb.core.data.source.local

import android.content.Context
import com.example.rawgamesdb.core.data.source.local.entity.GameEntity
import com.example.rawgamesdb.core.data.source.local.room.GameDao
import com.example.rawgamesdb.core.data.source.local.sharedpreferences.LoginPreference
import com.example.rawgamesdb.core.domain.model.LoginToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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

    fun getAllFavouritedGame(): Flow<List<GameEntity>> = gameDao.getAllFavouritedGame()

    fun deleteFavouriteGame(game: GameEntity) {
        gameDao.deleteFavouriteGame(game)
    }

    suspend fun insertFavouriteGame(game: GameEntity){
        game.favorite = true
        gameDao.insertFavouriteGame(game)
    }

    fun setLoginToken(token:String) = prefs.setLoginToken(token)

    fun getLoginToken():Flow<LoginToken> = flow{
        emit(LoginToken(token = prefs.getLoginToken()))
    }

    fun clearLoginToken() = prefs.clearLoginToken()
}