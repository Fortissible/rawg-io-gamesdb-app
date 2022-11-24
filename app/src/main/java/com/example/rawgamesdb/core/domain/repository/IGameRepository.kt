package com.example.rawgamesdb.core.domain.repository

import com.example.rawgamesdb.core.data.Resource
import com.example.rawgamesdb.core.domain.model.Game
import com.example.rawgamesdb.core.domain.model.GameDetail
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getAllGameFromApi(key:String): Flow<Resource<List<Game>>>
    fun getAllFavouritedGame(): Flow<List<Game>>
    fun getGameDetailFromApi(id:String,key:String): Flow<Resource<GameDetail>>
    fun deleteFavouriteGame(game:GameDetail)
    suspend fun insertFavourite(game: GameDetail)
}