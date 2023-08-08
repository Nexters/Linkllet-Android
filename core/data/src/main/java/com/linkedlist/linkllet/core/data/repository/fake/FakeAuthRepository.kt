package com.linkedlist.linkllet.core.data.repository.fake

import com.linkedlist.linkllet.core.data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAuthRepository : AuthRepository {
    override fun signUp(): Flow<Result<Boolean>> = flow {
        emit(Result.success(false))
    }
}