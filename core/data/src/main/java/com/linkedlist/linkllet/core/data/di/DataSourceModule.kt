package com.linkedlist.linkllet.core.data.di

import com.linkedlist.linkllet.core.data.remote.AuthRemoteDataSource
import com.linkedlist.linkllet.core.data.remote.AuthRemoteDataSourceImpl
import com.linkedlist.linkllet.core.data.remote.LinkRemoteDataSource
import com.linkedlist.linkllet.core.data.remote.LinkRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindAuthRemoteDataSource(
        authRemoteDataSource : AuthRemoteDataSourceImpl
    ) : AuthRemoteDataSource

    @Binds
    abstract fun bindLinkRemoteDataSource(
        linkRemoteDataSource : LinkRemoteDataSourceImpl
    ) : LinkRemoteDataSource

}