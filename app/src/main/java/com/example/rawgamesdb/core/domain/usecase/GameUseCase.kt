package com.example.rawgamesdb.core.domain.usecase

import com.example.rawgamesdb.core.data.Resource
import com.example.rawgamesdb.core.data.source.local.room.GameDao
import com.example.rawgamesdb.core.domain.model.Game
import com.example.rawgamesdb.core.domain.model.GameDetail
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getAllGameFromApi(key:String): Flow<Resource<List<Game>>>
    fun getGameDetailFromApi(id: String, key: String): Flow<Resource<GameDetail>>
    fun getAllFavouritedGame():Flow<List<Game>>
    fun deleteFavouriteGame(game: GameDetail)
    suspend fun insertFavourite(game: GameDetail)
}