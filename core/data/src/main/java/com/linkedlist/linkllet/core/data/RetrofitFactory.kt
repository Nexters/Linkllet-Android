package com.linkedlist.linkllet.core.data

import android.content.Context
import com.linkedlist.linkllet.core.data.remote.api.KeyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    inline fun <reified Service> create(
        context: Context
    ): Service {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BACKEND_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(createLoggingInterceptor())
                    .addInterceptor(KeyInterceptor(context))
                    .build()
            )
            .build()
            .create(Service::class.java)
    }

    fun createLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}