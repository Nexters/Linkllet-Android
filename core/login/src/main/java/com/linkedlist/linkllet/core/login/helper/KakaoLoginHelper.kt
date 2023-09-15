package com.linkedlist.linkllet.core.login.helper

import android.content.Context
import android.util.Log
import com.kakao.sdk.user.UserApiClient
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class KakaoLoginHelper @Inject constructor() : LoginHelper {

    override suspend fun login(
        context: Context
    ) : Result<String> {
        try {
            kakaoLogin(context)
            return Result.success(getKakaoUserId().toString())
        }catch (e:Exception){
            return Result.failure(e)
        }
    }

    private suspend fun kakaoLogin(context: Context) : Unit = suspendCoroutine { continuation ->
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                continuation.resumeWithException(error)
            } else if (token != null) {
                continuation.resume(Unit)
            } else {
                continuation.resumeWithException(RuntimeException("Kakao Login Error"))
            }
        }
    }

    private suspend fun getKakaoUserId() : Long = suspendCoroutine { continuation ->
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                continuation.resumeWithException(error)
            } else if (user?.id != null) {
                user.id?.let { userId ->
                    continuation.resume(userId)
                }
            } else {
                continuation.resumeWithException(RuntimeException("Kakao Id Error"))
            }
        }
    }


    override suspend fun logout(): Result<Unit> = suspendCoroutine { continuation ->
        UserApiClient.instance.logout { error ->
            if(error != null){
                continuation.resume(Result.failure(error))
            }else {
                continuation.resume(Result.success(Unit))
            }
        }
    }
}