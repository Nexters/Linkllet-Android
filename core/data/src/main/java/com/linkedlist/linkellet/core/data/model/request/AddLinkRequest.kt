package com.linkedlist.linkellet.core.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class AddLinkRequest(
    val name : String,
    val url : String
)