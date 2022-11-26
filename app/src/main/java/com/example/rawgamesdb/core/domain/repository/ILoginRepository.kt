package com.example.rawgamesdb.core.domain.repository

import com.example.rawgamesdb.core.data.Resource
import com.example.rawgamesdb.core.domain.model.LoginToken
import kotlinx.coroutines.flow.Flow

interface ILoginRepository {
    fun loginAccount(email:String,password:String): Flow<Resource<LoginToken>>
    fun logoutAccount()
    fun getLoginToken():Flow<LoginToken>
}