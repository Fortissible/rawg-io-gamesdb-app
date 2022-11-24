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
    companion object {
        @Volatile
        private var instance: LoginRepository ?= null
        fun getInstance(
            remoteSource:RemoteDataSource,
            localSource: LocalDataSource
        ): LoginRepository =
            instance ?: synchronized(this){
                instance ?: LoginRepository(remoteSource,localSource)
            }
    }

    override fun loginAccount(email: String, password: String,isRemembered:Boolean): Flow<Resource<LoginToken>> =
        object : NetworkBoundResource<LoginToken,LoginResponse>(){
            override fun loadFromLocal(): Flow<LoginToken> =
                localSource.getLoginToken()

            override fun shouldFetch(data: LoginToken?): Boolean =
                data?.token.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<LoginResponse>> =
                remoteSource.loginReqresApi(email,password)

            override suspend fun saveCallResult(data: LoginResponse) {
                if (isRemembered) localSource.setLoginToken(data.token)
            }

            override fun loadFromApi(data: LoginResponse): LoginToken =
                LoginToken(
                    token = data.token
                )

        }.asFlow()

    override fun logoutAccount() = localSource.clearLoginToken()

    override fun getLoginToken() : Flow<LoginToken> = localSource.getLoginToken()

}