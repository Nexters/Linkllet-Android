package com.linkedlist.linkellet.core.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class AddFolderRequest(
    val name: String
)