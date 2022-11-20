package com.example.rawgamesdb.core.data.source.remote.network

import com.example.rawgamesdb.core.data.source.remote.response.GameDetailResponse
import com.example.rawgamesdb.core.data.source.remote.response.GameResponse
import com.example.rawgamesdb.core.data.source.remote.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getGamesFromApi(
        @Query("key") key: String
    ): GameResponse

    @GET("games/{id}")
    suspend fun getGameDetailFromApi(
        @Path("id") id: String,
        @Query("key") key: String
    ): GameDetailResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email:String,
        @Field("password") password:String
    ): LoginResponse
}