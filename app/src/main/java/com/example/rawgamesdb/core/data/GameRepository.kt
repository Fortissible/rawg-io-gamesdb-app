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

    override fun getAllGame(key: String): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>,List<ResultsItem>>(){
            override fun loadFromDB(): Flow<List<Game>> {
                return localSource.getAllGame().map {
                    DataMapper.mapGamesEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                remoteSource.getAllGameFromApi(key = key)

            override fun loadFromSharedPrefs(): Flow<List<Game>> {
                TODO("Not yet implemented")
            }

        }.asFlow()

    override fun getAllFavouritedGame(): Flow<List<Game>> =
        localSource.getAllFavouritedGame().map { DataMapper.mapGamesEntitiesToDomain(it) }

    override fun setFavourite(game: Game, isFavourite: Boolean) {
        val gameEntity = DataMapper.mapGameDomainToEntities(game)
        localSource.updateFavouriteGame(gameEntity,isFavourite)
    }

    override suspend fun insertFavourite(game: Game) {
        val gameEntity = DataMapper.mapGameDomainToEntities(game)
        localSource.insertFavouriteGame(gameEntity)
    }

    override fun getGameDetail(id: String, key: String): Flow<Resource<Game>> =
        object : NetworkBoundResource<Game,GameDetailResponse>(){
            override fun loadFromDB(): Flow<Game> {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: Game?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<GameDetailResponse>> =
                remoteSource.getGameDetailFromApi(id,key)

            override fun loadFromSharedPrefs(): Flow<Game> {
                TODO("Not yet implemented")
            }
        }.asFlow()

}