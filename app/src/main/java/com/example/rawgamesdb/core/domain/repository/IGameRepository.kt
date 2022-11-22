package com.example.rawgamesdb.core.domain.repository

import com.example.rawgamesdb.core.data.Resource
import com.example.rawgamesdb.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getAllGameFromApi(key:String): Flow<Resource<List<Game>>>
    fun getAllFavouritedGame(): Flow<List<Game>>
    fun getGameDetailFromApi(id:String,key:String): Flow<Resource<Game>>
    fun deleteFavouriteGame(game:Game)
    suspend fun insertFavourite(game: Game)
}