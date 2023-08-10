package com.linkedlist.linkllet.core.data.remote.api

import com.linkedlist.linkllet.core.data.model.request.AddFeedbackRequest
import com.linkedlist.linkllet.core.data.model.request.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/api/v1/members")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ): Response<Unit>

    @POST("/api/v1/members/feedbacks")
    suspend fun addFeedback(
        @Body feedback: AddFeedbackRequest
    ): Response<Unit>
}