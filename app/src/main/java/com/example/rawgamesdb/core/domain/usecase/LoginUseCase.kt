package com.example.rawgamesdb.core.domain.usecase

import com.example.rawgamesdb.core.data.Resource
import com.example.rawgamesdb.core.domain.model.LoginToken
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    fun loginAccount(email: String, password: String, isRemembered:Boolean): Flow<Resource<LoginToken>>
    fun logoutAccount()
    fun getLoginToken():Flow<LoginToken>
}