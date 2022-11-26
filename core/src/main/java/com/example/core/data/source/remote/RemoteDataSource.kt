package com.example.core.data.source.remote

import android.util.Log
import com.example.core.data.source.remote.network.ApiRAWGService
import com.example.core.data.source.remote.network.ApiReqresService
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.GameDetailResponse
import com.example.core.data.source.remote.response.LoginResponse
import com.example.core.data.source.remote.response.ResultsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiRawgService: ApiRAWGService,
    private val apiReqresService: ApiReqresService
){

    suspend fun getAllGameFromApi(key:String) : Flow<ApiResponse<List<ResultsItem>>>{
        return flow {
            try {
                val response = apiRawgService.getGamesFromApi(key)
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
                val gameDetailResponse = apiRawgService.getGameDetailFromApi(id,key)
                if (gameDetailResponse.name.isNotEmpty()) emit(ApiResponse.Success(gameDetailResponse))
                else emit(ApiResponse.Empty)
            } catch(e:Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("Remote Data Source", "getGameDetailFromApi: $e")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun loginReqresApi(email:String, password:String): Flow<ApiResponse<LoginResponse>>{
        return flow {
            try {
                val loginResponse = apiReqresService.login(email,password)
                Log.d("RESPONSEEE", "loginReqresApi: ${loginResponse.token}")
                if (loginResponse.token.isNotEmpty())
                    emit(ApiResponse.Success(loginResponse))
                else
                    emit(ApiResponse.Empty)
            } catch(e:Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("Remote Data Source", "loginReqresApi: $e")
            }
        }.flowOn(Dispatchers.IO)
    }
}