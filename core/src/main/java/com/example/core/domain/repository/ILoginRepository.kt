package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.LoginToken
import kotlinx.coroutines.flow.Flow

interface ILoginRepository {
    fun loginAccount(email:String,password:String): Flow<Resource<LoginToken>>
    fun logoutAccount()
    fun getLoginToken():Flow<LoginToken>
}