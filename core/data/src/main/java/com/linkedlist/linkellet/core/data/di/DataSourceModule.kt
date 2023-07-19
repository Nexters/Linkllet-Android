package com.linkedlist.linkellet.core.data.di

import com.linkedlist.linkellet.core.data.source.remote.AuthRemoteDataSource
import com.linkedlist.linkellet.core.data.source.remote.AuthRemoteDataSourceImpl
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

}