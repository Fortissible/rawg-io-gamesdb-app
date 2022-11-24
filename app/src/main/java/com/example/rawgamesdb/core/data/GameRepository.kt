package com.example.rawgamesdb.core.data

import android.util.Log
import com.example.rawgamesdb.core.data.source.local.LocalDataSource
import com.example.rawgamesdb.core.data.source.remote.RemoteDataSource
import com.example.rawgamesdb.core.data.source.remote.network.ApiResponse
import com.example.rawgamesdb.core.data.source.remote.response.GameDetailResponse
import com.example.rawgamesdb.core.domain.model.Game
import com.example.rawgamesdb.core.domain.repository.IGameRepository
import com.example.rawgamesdb.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
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

    override fun getAllGameFromApi(key: String): Flow<Resource<List<Game>>> = flow {
        emit(Resource.Loading())
        when (val response = remoteSource.getAllGameFromApi(key = key).first()){
            is ApiResponse.Empty -> {
                Log.d("KOSONG GAN", "onViewCreated: ")
                emit(Resource.Success(ArrayList()))
            }
            is ApiResponse.Success -> {
                val gameList = DataMapper.mapGameResponseToDomain(response.data)
                emit(Resource.Success(gameList))
            }
            is ApiResponse.Error -> {
                Log.d("ERROR GAN", "onViewCreated: ")
                emit(Resource.Error(response.errorMessage))
            }
        }
    }

    override fun getGameDetail(id: String, key: String): Flow<Resource<Game>> =
        object :NetworkBoundResource<Game,GameDetailResponse>(){
            override fun loadFromLocal(): Flow<Game> = flow{
                emit(
                    DataMapper.mapGameEntitiesToDomain(
                        localSource.getFavouritedGame(id.toInt()).first()
                    )
                )
            }

            override fun shouldFetch(data: Game?): Boolean =
                data?.name.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<GameDetailResponse>> =
                remoteSource.getGameDetailFromApi(id,key)

            override suspend fun saveCallResult(data: GameDetailResponse) {
                Log.d("NOT SAVING", "saveCallResult: ")
            }

            override fun loadFromApi(data: GameDetailResponse): Game =
                DataMapper.mapGameDetailResponseToDomain(data)

        }.asFlow()

//        flow {
//        emit(Resource.Loading())
//        when (val response = remoteSource.getGameDetailFromApi(id,key).first()){
//            is ApiResponse.Empty -> {
//                Log.d("KOSONG GAN", "onViewCreated: ")
//                val emptyData = DataMapper.getGameDetailEmpty()
//                emit(Resource.Success(emptyData))
//            }
//            is ApiResponse.Success -> {
//                val gameDetail = DataMapper.mapGameDetailResponseToDomain(response.data)
//                emit(Resource.Success(gameDetail))
//            }
//            is ApiResponse.Error -> {
//                Log.d("ERROR GAN", "onViewCreated: ")
//                emit(Resource.Error(response.errorMessage))
//            }
//        }
//    }

    override fun getAllFavouritedGame(): Flow<List<Game>> =
        localSource.getAllFavouritedGame()
            .map { DataMapper.mapGamesEntitiesToDomain(it) }

    override suspend fun deleteFavouriteGame(game: Game) {
        val gameEntity = DataMapper.mapGameDomainToEntities(game)
        localSource.deleteFavouriteGame(gameEntity)
    }

    override suspend fun insertFavourite(game: Game) {
        val gameEntity = DataMapper.mapGameDomainToEntities(game)
        localSource.insertFavouriteGame(gameEntity)
    }
}