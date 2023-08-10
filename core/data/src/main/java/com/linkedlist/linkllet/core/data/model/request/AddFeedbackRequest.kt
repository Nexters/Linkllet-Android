package com.linkedlist.linkllet.core.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class AddFeedbackRequest(
    val feedback: String,
)