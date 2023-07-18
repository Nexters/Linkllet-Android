package com.linkedlist.linkellet.core.data

import com.linkedlist.linkllet.core.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    //private const val apiVersion = "v1"
    //const val baseUrl = "${BuildConfig.BACKEND_URL}/api/$apiVersion/"

    inline fun <reified Service> create(): Service {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BACKEND_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(createLoggingInterceptor())
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