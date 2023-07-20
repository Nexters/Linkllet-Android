package com.linkedlist.linkellet.core.data.source.remote

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.util.Log
import com.linkedlist.linkellet.core.data.model.Auth
import com.linkedlist.linkellet.core.data.model.request.SignUpRequest
import com.linkedlist.linkellet.core.data.source.remote.api.AuthService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val authService: AuthService
) : AuthRemoteDataSource {

    @SuppressLint("HardwareIds")
    override suspend fun signUp(): Result<Auth> {
        return try {
            val response = authService.signUp(
                SignUpRequest(
                    deviceId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
                )
            )
            if(response.isSuccessful){
                Result.success(Auth.SIGNED_UP)
            }else {
                if(response.code() == 409) Result.success(Auth.ALREADY_SIGNED_UP)
                else Result.failure(Exception())
            }
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}