package com.example.rawgamesdb.features.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.rawgamesdb.core.data.Resource
import com.example.rawgamesdb.core.domain.model.LoginToken
import com.example.rawgamesdb.core.domain.usecase.LoginUseCase

class LoginViewModel (
    private val loginUseCase: LoginUseCase
): ViewModel() {

    val loginRAWGame:(email:String,password:String,isRemembered:Boolean)
    -> LiveData<Resource<LoginToken>> = { email,password,isRemembered ->
        loginUseCase.loginAccount(email,password,isRemembered).asLiveData()
    }

    val getToken = loginUseCase.getLoginToken().asLiveData()
}