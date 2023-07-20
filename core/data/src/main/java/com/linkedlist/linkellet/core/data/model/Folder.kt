package com.linkedlist.linkellet.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Folder(
    val id : Long,
    val name : String,
    val type : String
)
