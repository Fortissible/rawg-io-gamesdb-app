package com.example.rawgamesdb.core.data

import com.example.rawgamesdb.core.data.source.local.LocalDataSource
import com.example.rawgamesdb.core.data.source.remote.RemoteDataSource
import com.example.rawgamesdb.core.data.source.remote.network.ApiResponse
import com.example.rawgamesdb.core.data.source.remote.response.GameDetailResponse
import com.example.rawgamesdb.core.data.source.remote.response.ResultsItem
import com.example.rawgamesdb.core.domain.model.Game
import com.example.rawgamesdb.core.domain.repository.IGameRepository
import com.example.rawgamesdb.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository private constructor(
    private val remoteSource : RemoteDataSource,
    private val localSource : LocalDataSource
): IGameRepository{
    companion object {
        @Volatile
        private var instance: GameRepository ?= null
        fun getInstance(
            remoteSource:RemoteDataSource,
            localSource: LocalDataSource
        ): GameRepository =
            instance ?: synchronized(this){
                instance ?: GameRepository(remoteSource,localSource)
            }
    }

    override fun getAllGameFromApi(key: String): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>,List<ResultsItem>>(){
            override fun loadFromPrefs(): Flow<List<Game>> {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: List<Game>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                remoteSource.getAllGameFromApi(key = key)

        }.asFlow()

    override fun getGameDetailFromApi(id: String, key: String): Flow<Resource<Game>> =
        object : NetworkBoundResource<Game,GameDetailResponse>(){
            override fun loadFromPrefs(): Flow<Game> {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: Game?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<GameDetailResponse>> =
                remoteSource.getGameDetailFromApi(id,key)

        }.asFlow()

    override fun getAllFavouritedGame(): Flow<List<Game>> =
        localSource.getAllFavouritedGame().map { DataMapper.mapGamesEntitiesToDomain(it) }

    override fun deleteFavouriteGame(game: Game) {
        val gameEntity = DataMapper.mapGameDomainToEntities(game)
        localSource.deleteFavouriteGame(gameEntity)
    }

    override suspend fun insertFavourite(game: Game) {
        val gameEntity = DataMapper.mapGameDomainToEntities(game)
        localSource.insertFavouriteGame(gameEntity)
    }
}