package com.example.rawgamesdb.core.domain.usecase

import com.example.rawgamesdb.core.domain.model.LoginToken
import com.example.rawgamesdb.core.domain.repository.ILoginRepository
import kotlinx.coroutines.flow.Flow

class LoginInteractor (private val loginRepository: ILoginRepository):LoginUseCase{
    override fun loginAccount(email: String, password: String,isRemembered:Boolean) =
        loginRepository.loginAccount(email,password,isRemembered)

    override fun logoutAccount() =
        loginRepository.logoutAccount()

    override fun getLoginToken(): Flow<LoginToken> =
        loginRepository.getLoginToken()
}