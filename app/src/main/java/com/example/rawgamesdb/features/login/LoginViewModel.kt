package com.example.rawgamesdb.features.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.rawgamesdb.core.data.Resource
import com.example.rawgamesdb.core.domain.model.LoginToken
import com.example.rawgamesdb.core.domain.usecase.LoginUseCase

class LoginViewModel (
    private val loginUseCase: LoginUseCase
): ViewModel() {

    private val _tokenLogin = MutableLiveData<Resource<LoginToken>>()
    val tokenLogin : LiveData<Resource<LoginToken>> = _tokenLogin

    fun loginRAWGame(email:String,password:String) {
        _tokenLogin.value = loginUseCase.loginAccount(email,password).asLiveData().value
    }
}