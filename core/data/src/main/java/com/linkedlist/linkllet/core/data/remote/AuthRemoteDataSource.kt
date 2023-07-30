package com.linkedlist.linkllet.core.data.source.remote

import com.linkedlist.linkllet.core.data.model.Auth

interface AuthRemoteDataSource {
    suspend fun signUp() : Result<Auth>
}