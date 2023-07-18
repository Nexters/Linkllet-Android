package com.linkedlist.linkellet.core.data.di

import com.linkedlist.linkellet.core.data.RetrofitFactory
import com.linkedlist.linkellet.core.data.source.remote.api.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideAuthService(): AuthService
     = RetrofitFactory.create()
}