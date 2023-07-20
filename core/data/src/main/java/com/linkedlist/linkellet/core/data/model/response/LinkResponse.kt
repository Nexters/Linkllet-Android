package com.linkedlist.linkellet.core.data.model.response

import com.linkedlist.linkellet.core.data.model.Link
import kotlinx.serialization.Serializable

@Serializable
data class LinkResponse(
    val folderList : List<Link>
)