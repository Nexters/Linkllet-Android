package com.linkedlist.linkellet.core.data.model.response

import com.linkedlist.linkellet.core.data.model.Folder
import kotlinx.serialization.Serializable

@Serializable
data class FolderResponse(
    val folderList : List<Folder>
)
