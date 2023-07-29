package com.linkedlist.linkllet.core.data.model.response

import com.linkedlist.linkllet.core.data.model.Folder
import kotlinx.serialization.Serializable

@Serializable
data class FolderResponse(
    val folderList : List<Folder>
)
