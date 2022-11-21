package com.example.rawgamesdb.core.data

import com.example.rawgamesdb.core.data.source.local.LocalDataSource
import com.example.rawgamesdb.core.data.source.remote.RemoteDataSource
import com.example.rawgamesdb.core.data.source.remote.network.ApiResponse
import com.example.rawgamesdb.core.data.source.remote.response.LoginResponse
import com.example.rawgamesdb.core.domain.model.LoginToken
import com.example.rawgamesdb.core.domain.repository.ILoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginRepository private constructor(
    private val remoteSource : RemoteDataSource,
    private val localSource : LocalDataSource
): ILoginRepository {
    override fun loginAccount(email: String, password: String): Flow<Resource<LoginToken>> =
        object : NetworkBoundResource<LoginToken,LoginResponse>(){
            override fun loadFromDB(): Flow<LoginToken> {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: LoginToken?): Boolean {
                return data == null
            }

            override suspend fun createCall(): Flow<ApiResponse<LoginResponse>> =
                // TODO : set login token!!
                remoteSource.loginReqresApi(email,password)


            override fun loadFromSharedPrefs(): Flow<LoginToken> = flow {
                localSource.getLoginToken()
            }
        }.asFlow()

    override fun logoutAccount(): Flow<Boolean> = flow {
        localSource.clearLoginToken()
    }

}