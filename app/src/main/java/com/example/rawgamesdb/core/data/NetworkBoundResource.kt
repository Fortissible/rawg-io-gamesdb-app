package com.example.rawgamesdb.core.data

import com.example.rawgamesdb.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromLocal().first()
        if (shouldFetch(dbSource)) {
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emit(Resource.Success(loadFromApi(apiResponse.data)))
                }
                is ApiResponse.Empty -> {

                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromLocal().map { Resource.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromLocal(): Flow<ResultType>

    protected abstract fun loadFromApi(data:RequestType): ResultType

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun saveCallResult(data: RequestType)

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    fun asFlow(): Flow<Resource<ResultType>> = result
}