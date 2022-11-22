package com.example.rawgamesdb.core.data

import androidx.lifecycle.asLiveData
import com.example.rawgamesdb.core.data.source.local.LocalDataSource
import com.example.rawgamesdb.core.data.source.remote.RemoteDataSource
import com.example.rawgamesdb.core.data.source.remote.network.ApiResponse
import com.example.rawgamesdb.core.data.source.remote.response.LoginResponse
import com.example.rawgamesdb.core.domain.model.LoginToken
import com.example.rawgamesdb.core.domain.repository.ILoginRepository
import kotlinx.coroutines.flow.*

class LoginRepository private constructor(
    private val remoteSource : RemoteDataSource,
    private val localSource : LocalDataSource
): ILoginRepository {
    override fun loginAccount(email: String, password: String): Flow<Resource<LoginToken>> =
        object : NetworkBoundResource<LoginToken,LoginResponse>(){
            override fun loadFromPrefs(): Flow<LoginToken> = localSource.getLoginToken()

            override fun shouldFetch(data: LoginToken?): Boolean = data?.token.isNullOrEmpty()


            override suspend fun createCall(): Flow<ApiResponse<LoginResponse>> {
                val response = remoteSource.loginReqresApi(email,password)
                setLoginToken(response.first())
                return response
            }

        }.asFlow()

    override fun logoutAccount(): Flow<Boolean> = flow {
        localSource.clearLoginToken()
    }

    override fun setLoginToken(response: ApiResponse<LoginResponse>) {
        when (response) {
            is ApiResponse.Success -> {
                localSource.setLoginToken(response.data.token)
            }
            else -> {
                localSource.setLoginToken("")
            }
        }
    }

}