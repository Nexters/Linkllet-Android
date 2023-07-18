package com.linkedlist.linkellet.core.data.source.remote

import com.linkedlist.linkellet.core.data.model.Auth

interface AuthRemoteDataSource {
    suspend fun signUp() : Result<Auth>
}