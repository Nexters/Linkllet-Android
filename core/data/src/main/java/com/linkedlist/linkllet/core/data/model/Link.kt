package com.linkedlist.linkllet.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Link(
    val id : Long,
    val name : String,
    val url : String,
    val createAt : String
)
