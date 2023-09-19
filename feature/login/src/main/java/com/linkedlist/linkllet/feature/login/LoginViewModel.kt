package com.linkedlist.linkllet.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkedlist.linkllet.core.data.repository.AuthRepository
import com.linkedlist.linkllet.core.login.LoginManager
import com.linkedlist.linkllet.core.login.LoginType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginManager: LoginManager
) : ViewModel() {

    fun kakaoLogin(){
        viewModelScope.launch {
            loginManager.setLoginState(LoginType.KAKAO)
        }
    }

    fun guestLogin(){
        viewModelScope.launch {
            loginManager.setLoginState(LoginType.GUEST)
        }
    }
}