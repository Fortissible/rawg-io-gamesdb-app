package com.example.rawgamesdb.core.domain.usecase

import com.example.rawgamesdb.core.domain.repository.ILoginRepository
import kotlinx.coroutines.flow.Flow

class LoginInteractor (private val loginRepository: ILoginRepository):LoginUseCase{
    override fun loginAccount(email: String, password: String, isClicked:Boolean) =
        loginRepository.loginAccount(email,password, isClicked)

    override fun logoutAccount() =
        loginRepository.logoutAccount()
}