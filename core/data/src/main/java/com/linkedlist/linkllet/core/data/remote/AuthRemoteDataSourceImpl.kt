package com.linkedlist.linkllet.core.data.remote

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.linkedlist.linkllet.core.data.model.Auth
import com.linkedlist.linkllet.core.data.model.request.AddFeedbackRequest
import com.linkedlist.linkllet.core.data.model.request.SignUpRequest
import com.linkedlist.linkllet.core.data.remote.api.AuthService
import com.linkedlist.linkllet.core.login.LoginManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService,
    private val loginManager: LoginManager
) : AuthRemoteDataSource {

    @SuppressLint("HardwareIds")
    override suspend fun signUp(): Auth {
        val response = authService.signUp(
            SignUpRequest(
                deviceId = loginManager.deviceId
            )
        )

        return when {
            response.isSuccessful -> Auth.SIGNED_UP
            response.code() == 409 -> Auth.ALREADY_SIGNED_UP
            else -> throw RuntimeException("회원가입에 실패했어요.")
        }
    }

    override suspend fun addFeedback(content: String) {
        val response = authService.addFeedback(AddFeedbackRequest(feedback = content))
        if(!response.isSuccessful) throw RuntimeException("피드백 전송에 실패했어요.")
    }
}