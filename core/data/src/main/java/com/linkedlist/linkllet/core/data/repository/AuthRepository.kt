package com.linkedlist.linkllet.core.data.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun signUp(): Flow<Boolean>
    fun addFeedback(content: String): Flow<Unit>
}