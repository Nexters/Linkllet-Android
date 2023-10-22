package com.linkedlist.linkllet.core.data.remote

import com.linkedlist.linkllet.core.data.model.Folder
import com.linkedlist.linkllet.core.data.model.Link
import com.linkedlist.linkllet.core.data.model.request.AddLinkRequest

interface LinkRemoteDataSource {

    suspend fun addFolder(name: String)

    suspend fun getFolders(): List<Folder>

    suspend fun getLinks(id: Long): List<Link>

    suspend fun search(query: String): List<Link>

    suspend fun addLink(
        id: Long,
        addLinkRequest: AddLinkRequest
    )

    suspend fun deleteFolder(
        id: Long
    )

    suspend fun deleteLink(
        id: Long,
        articleId: Long
    )

}