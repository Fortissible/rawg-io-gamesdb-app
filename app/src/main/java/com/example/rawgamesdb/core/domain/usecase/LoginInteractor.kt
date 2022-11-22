package com.example.rawgamesdb.core.domain.usecase

import com.example.rawgamesdb.core.domain.repository.ILoginRepository
import kotlinx.coroutines.flow.Flow

class LoginInteractor (private val loginRepository: ILoginRepository):LoginUseCase{
    override fun loginAccount(email: String, password: String) =
        loginRepository.loginAccount(email,password)

    override fun logoutAccount() =
        loginRepository.logoutAccount()
}