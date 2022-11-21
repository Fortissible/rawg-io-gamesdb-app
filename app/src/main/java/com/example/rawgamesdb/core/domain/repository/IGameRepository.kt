package com.example.rawgamesdb.core.domain.repository

import com.example.rawgamesdb.core.data.Resource
import com.example.rawgamesdb.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getAllGame(key:String): Flow<Resource<List<Game>>>
    fun getAllFavouritedGame(): Flow<List<Game>>
    fun getGameDetail(id:String,key:String): Flow<Resource<Game>>
    fun setFavourite(game:Game,isFavourite:Boolean)
    suspend fun insertFavourite(game: Game)
}