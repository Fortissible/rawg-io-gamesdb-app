package com.example.rawgamesdb.core.data

import com.example.rawgamesdb.core.data.source.local.LocalDataSource
import com.example.rawgamesdb.core.data.source.remote.RemoteDataSource

class GameRepository private constructor(
    private val remoteSource : RemoteDataSource,
    private val localSource : LocalDataSource
){
}