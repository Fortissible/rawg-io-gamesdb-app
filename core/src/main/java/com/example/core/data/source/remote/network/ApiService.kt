package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.GameDetailResponse
import com.example.core.data.source.remote.response.GameResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRAWGService {
    @GET("games")
    suspend fun getGamesFromApi(
        @Query("key") key: String
    ): GameResponse

    @GET("games/{id}")
    suspend fun getGameDetailFromApi(
        @Path("id") id: String,
        @Query("key") key: String
    ): GameDetailResponse
}