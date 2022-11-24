package com.example.rawgamesdb.core.domain.usecase

import com.example.rawgamesdb.core.data.Resource
import com.example.rawgamesdb.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getAllGameFromApi(key:String): Flow<Resource<List<Game>>>
    fun getGameDetail(id: String, key: String): Flow<Resource<Game>>
    fun getAllFavouritedGame():Flow<List<Game>>
    suspend fun deleteFavouriteGame(game: Game)
    suspend fun insertFavourite(game: Game)
}