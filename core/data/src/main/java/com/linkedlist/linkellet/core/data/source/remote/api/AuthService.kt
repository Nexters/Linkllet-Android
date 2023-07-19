package com.linkedlist.linkellet.core.data.source.remote.api

import com.linkedlist.linkellet.core.data.model.request.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/api/v1/members")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ) : Response<Unit>
}