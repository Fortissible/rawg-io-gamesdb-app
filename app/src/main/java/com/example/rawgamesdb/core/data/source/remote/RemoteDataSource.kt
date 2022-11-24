package com.example.rawgamesdb.core.data.source.remote

import android.util.Log
import com.example.rawgamesdb.core.data.source.remote.network.ApiResponse
import com.example.rawgamesdb.core.data.source.remote.network.ApiService
import com.example.rawgamesdb.core.data.source.remote.response.GameDetailResponse
import com.example.rawgamesdb.core.data.source.remote.response.LoginResponse
import com.example.rawgamesdb.core.data.source.remote.response.ResultsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource private constructor(
    private val apiService: ApiService
){
    companion object {
        private var instance: RemoteDataSource ?= null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource(service)
            }
    }

    suspend fun getAllGameFromApi(key:String) : Flow<ApiResponse<List<ResultsItem>>>{
        return flow {
            try {
                val response = apiService.getGamesFromApi(key)
                val gameList = response.results

                if (gameList.isNotEmpty()) emit(ApiResponse.Success(gameList))
                else emit(ApiResponse.Empty)
            } catch(e:Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("Remote Data Source", "getAllGameFromApi: $e")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGameDetailFromApi(id:String,key:String) : Flow<ApiResponse<GameDetailResponse>>{
        return flow {
            try {
                val gameDetailResponse = apiService.getGameDetailFromApi(id,key)
                emit(ApiResponse.Success(gameDetailResponse))
            } catch(e:Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("Remote Data Source", "getGameDetailFromApi: $e")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun loginReqresApi(email:String, password:String): Flow<ApiResponse<LoginResponse>>{
        return flow {
            try {
                val loginResponse = apiService.login(email,password)
                Log.d("RESPONSEEE", "loginReqresApi: ${loginResponse.token}")
                if (loginResponse.token.isNotEmpty()) emit(ApiResponse.Success(loginResponse))
                else emit(ApiResponse.Empty)
            } catch(e:Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("Remote Data Source", "loginReqresApi: $e")
            }
        }.flowOn(Dispatchers.IO)
    }
}