package com.example.core.data.source.local

import android.content.SharedPreferences
import android.util.Log
import com.example.core.data.source.local.entity.GameEntity
import com.example.core.data.source.local.room.GameDao
import com.example.core.domain.model.LoginToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val gameDao: GameDao,
    private val prefs: SharedPreferences
){

    fun getAllFavouritedGame(): Flow<List<GameEntity>> = gameDao.getAllFavouritedGame()

    suspend fun deleteFavouriteGame(game: GameEntity) {
        gameDao.deleteFavouriteGame(game)
    }

    suspend fun insertFavouriteGame(game: GameEntity){
        game.favorite = true
        gameDao.insertFavouriteGame(game)
    }

    fun getFavouritedGame(id:Int) = gameDao.getFavouritedGame(id)

    fun setLoginToken(token:String){
        Log.d("AAA", "setLoginToken: ")
        val editor = prefs.edit()
        editor.putString(LOGIN_TOKEN,token)
        editor.apply()
    }

    fun getLoginToken():Flow<LoginToken> = flow {
        Log.d("BBB", "getLoginToken: ")
        emit(LoginToken(
            token = prefs.getString(LOGIN_TOKEN,"")
        ))
    }

    fun clearLoginToken(){
        Log.d("CCC", "clearLoginToken: ")
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private const val LOGIN_TOKEN = "login_token"
    }
}