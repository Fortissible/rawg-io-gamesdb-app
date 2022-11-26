package com.example.rawgamesdb.features.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.rawgamesdb.core.data.Resource
import com.example.rawgamesdb.core.domain.model.LoginToken
import com.example.rawgamesdb.core.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    loginUseCase: LoginUseCase
): ViewModel() {

    val loginRAWGame:(email:String,password:String)
    -> LiveData<Resource<LoginToken>> = { email,password->
        loginUseCase.loginAccount(email,password).asLiveData()
    }

    val getToken = loginUseCase.getLoginToken().asLiveData()
}