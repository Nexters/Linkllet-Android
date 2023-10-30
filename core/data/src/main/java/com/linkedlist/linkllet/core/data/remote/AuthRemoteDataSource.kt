package com.linkedlist.linkllet.core.data.remote

import com.linkedlist.linkllet.core.data.model.Auth

interface AuthRemoteDataSource {
    suspend fun signUp(): Auth
    suspend fun addFeedback(content: String)
}