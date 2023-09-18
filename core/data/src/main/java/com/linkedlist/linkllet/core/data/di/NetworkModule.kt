package com.linkedlist.linkllet.core.data.di

import android.content.Context
import com.linkedlist.linkllet.core.data.RetrofitFactory
import com.linkedlist.linkllet.core.data.remote.api.AuthService
import com.linkedlist.linkllet.core.data.remote.api.KeyInterceptor
import com.linkedlist.linkllet.core.data.remote.api.LinkService
import com.linkedlist.linkllet.core.data.repository.AuthRepositoryImpl
import com.linkedlist.linkllet.core.login.LoginManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideKeyInterceptor(
        loginManager: LoginManager
    ) : KeyInterceptor {
        return KeyInterceptor(loginManager)
    }

    @Provides
    @Singleton
    fun provideAuthService(
        keyInterceptor: KeyInterceptor
    ): AuthService
        = RetrofitFactory.create(keyInterceptor)

    @Provides
    @Singleton
    fun provideLinkService(
        keyInterceptor: KeyInterceptor
    ): LinkService
        = RetrofitFactory.create(keyInterceptor)


}