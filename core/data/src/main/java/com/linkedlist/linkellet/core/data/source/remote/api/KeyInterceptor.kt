package com.linkedlist.linkellet.core.data.source.remote.api

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import okhttp3.Interceptor
import okhttp3.Response

class KeyInterceptor(
    private val context: Context
) : Interceptor {
    @SuppressLint("HardwareIds")
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder().apply {
            header("Device-Id", Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID))
            method(original.method, original.body)
        }.build()
        return chain.proceed(request)
    }
}