package com.linkedlist.linkllet.core.data.remote

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.linkedlist.linkllet.core.data.model.Auth
import com.linkedlist.linkllet.core.data.model.request.AddFeedbackRequest
import com.linkedlist.linkllet.core.data.model.request.SignUpRequest
import com.linkedlist.linkllet.core.data.remote.api.AuthService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val authService: AuthService
) : AuthRemoteDataSource {

    @SuppressLint("HardwareIds")
    override suspend fun signUp(): Result<Auth> = runCatching {
        val response = authService.signUp(
            SignUpRequest(
                deviceId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            )
        )

        when {
            response.isSuccessful -> Auth.SIGNED_UP
            response.code() == 409 -> Auth.ALREADY_SIGNED_UP
            else -> throw RuntimeException("회원가입에 실패했어요.")
        }
    }

    override suspend fun addFeedback(content: String): Result<Unit> = runCatching {
        val response = authService.addFeedback(AddFeedbackRequest(feedback = content))
        if(!response.isSuccessful) throw RuntimeException("피드백 전송에 실패했어요.")
    }
}