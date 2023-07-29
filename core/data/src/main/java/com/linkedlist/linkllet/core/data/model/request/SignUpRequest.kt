package com.linkedlist.linkllet.core.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    val deviceId : String
)
