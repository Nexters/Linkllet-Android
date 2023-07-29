package com.linkedlist.linkllet.core.data.di

import android.content.Context
import com.linkedlist.linkllet.core.data.RetrofitFactory
import com.linkedlist.linkllet.core.data.source.remote.api.AuthService
import com.linkedlist.linkllet.core.data.source.remote.api.LinkService
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
    fun provideAuthService(
        @ApplicationContext context : Context
    ): AuthService
        = RetrofitFactory.create(context)

    @Provides
    @Singleton
    fun provideLinkService(
        @ApplicationContext context : Context
    ): LinkService
        = RetrofitFactory.create(context)
}