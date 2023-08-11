package com.linkedlist.linkllet.core.data.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signUp(): Flow<Result<Boolean>>
    fun addFeedback(content: String): Flow<Result<Unit>>
}