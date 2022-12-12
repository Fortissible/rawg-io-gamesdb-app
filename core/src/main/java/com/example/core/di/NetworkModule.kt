package com.example.core.di

import com.example.core.data.source.remote.network.ApiRAWGService
import com.example.core.data.source.remote.network.ApiReqresService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient():OkHttpClient {
        val reqresHostname = "reqres.in"
        val rawgioHostname = "api.rawg.io"
        val certificatePinner : CertificatePinner = CertificatePinner.Builder()
            .add(reqresHostname,"sha256/GLtD2rLgK2k2kFpghasXunum9rNP/xowCP8LxztyA2A=")
            .add(reqresHostname,"sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .add(reqresHostname,"sha256/Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o=")
            .add(rawgioHostname,"sha256/6i+nf58l8neEnNarZOvxiYfVbt2S2xurswGQQBBMa0U=")
            .add(rawgioHostname,"sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .add(rawgioHostname,"sha256/Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o=")
            .build()
        return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .certificatePinner(certificatePinner)
        .build()
    }

    @Provides
    fun provideRAWGamesApiService(client: OkHttpClient):ApiRAWGService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiRAWGService::class.java)
    }

    @Provides
    fun provideReqresApiService(client: OkHttpClient):ApiReqresService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiReqresService::class.java)
    }
}