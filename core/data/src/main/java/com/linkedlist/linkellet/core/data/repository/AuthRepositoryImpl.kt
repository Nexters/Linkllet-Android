package com.linkedlist.linkellet.core.data.repository

import com.linkedlist.linkellet.core.data.model.Auth.*
import com.linkedlist.linkellet.core.data.source.remote.AuthRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {
    override fun signUp(): Flow<Result<Boolean>> = flow {
        authRemoteDataSource.signUp()
            .onFailure {
                emit(Result.failure(it))
            }.onSuccess {
                when (it) {
                    SIGNED_UP -> emit(Result.success(true))
                    ALREADY_SIGNED_UP -> emit(Result.success(true))
                    FAILED -> emit(Result.success(false))
                }
            }
    }
}