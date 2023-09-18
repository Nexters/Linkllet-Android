package com.linkedlist.linkllet.core.data.repository.fake

import com.linkedlist.linkllet.core.data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class FakeAuthRepository : AuthRepository {

    override fun signUp(): Flow<Result<Boolean>> {
        TODO("Not yet implemented")
    }

    override fun addFeedback(content: String): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }
}