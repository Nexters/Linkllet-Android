package com.linkedlist.linkllet.core.data.remote

import com.linkedlist.linkllet.core.data.model.Auth

interface AuthRemoteDataSource {
    suspend fun signUp(): Result<Auth>
    suspend fun addFeedback(content: String)
}