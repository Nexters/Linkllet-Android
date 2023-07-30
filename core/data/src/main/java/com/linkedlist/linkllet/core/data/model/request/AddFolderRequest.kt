package com.linkedlist.linkllet.core.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class AddFolderRequest(
    val name: String
)