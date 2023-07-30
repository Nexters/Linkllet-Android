package com.linkedlist.linkllet.core.data.model.response

import com.linkedlist.linkllet.core.data.model.Link
import kotlinx.serialization.Serializable

@Serializable
data class LinkResponse(
    val articleList : List<Link>
)