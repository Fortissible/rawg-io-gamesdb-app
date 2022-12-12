package com.example.core.domain.usecase

import com.example.core.domain.model.LoginToken
import com.example.core.domain.repository.ILoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginInteractor @Inject constructor(private val loginRepository: ILoginRepository):LoginUseCase{
    override fun loginAccount(email: String, password: String) =
        loginRepository.loginAccount(email,password)

    override fun logoutAccount() =
        loginRepository.logoutAccount()

    override fun getLoginToken(): Flow<LoginToken> =
        loginRepository.getLoginToken()
}