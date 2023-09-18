package com.linkedlist.linkllet.core.data.remote.api

import android.util.Log
import com.linkedlist.linkllet.core.login.LoginManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class KeyInterceptor @Inject constructor(
    private val loginManager: LoginManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder().apply {
            header("Device-Id", loginManager.deviceId)
            method(original.method, original.body)
        }.build()
        return chain.proceed(request)
    }
}