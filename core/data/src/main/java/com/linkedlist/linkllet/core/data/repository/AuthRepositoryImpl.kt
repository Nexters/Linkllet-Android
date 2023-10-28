package com.linkedlist.linkllet.core.data.repository

import com.linkedlist.linkllet.core.data.model.Auth.ALREADY_SIGNED_UP
import com.linkedlist.linkllet.core.data.model.Auth.SIGNED_UP
import com.linkedlist.linkllet.core.data.remote.AuthRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override fun signUp(): Flow<Result<Boolean>> = flow {
        emit(authRemoteDataSource.signUp().mapCatching { auth ->
            when (auth) {
                SIGNED_UP, ALREADY_SIGNED_UP -> {
                    true
                }
                else -> false
            }
        })
    }

    override fun addFeedback(content: String): Flow<Unit> = flow {
        val response = authRemoteDataSource.addFeedback(content)
        emit(response)
    }
}