package com.linkedlist.linkellet.core.data.di

import com.linkedlist.linkellet.core.data.repository.AuthRepository
import com.linkedlist.linkellet.core.data.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(
        authRepository: AuthRepositoryImpl
    ) : AuthRepository
}