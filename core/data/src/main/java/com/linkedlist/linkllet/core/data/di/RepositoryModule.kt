package com.linkedlist.linkllet.core.data.di

import com.linkedlist.linkllet.core.data.repository.AuthRepository
import com.linkedlist.linkllet.core.data.repository.AuthRepositoryImpl
import com.linkedlist.linkllet.core.data.repository.LinkRepository
import com.linkedlist.linkllet.core.data.repository.LinkRepositoryImpl
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

    @Binds
    abstract fun bindLinkRepository(
        linkRepository: LinkRepositoryImpl
    ) : LinkRepository
}