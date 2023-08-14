package com.linkedlist.linkllet.core.data.remote

import com.linkedlist.linkllet.core.data.model.Folder
import com.linkedlist.linkllet.core.data.model.Link
import com.linkedlist.linkllet.core.data.model.request.AddLinkRequest

interface LinkRemoteDataSource {

    suspend fun addFolder(name: String): Result<Unit>

    suspend fun getFolders() : Result<List<Folder>>

    suspend fun getLinks(id : Long) : Result<List<Link>>

    suspend fun search(query: String): Result<List<Link>>

    suspend fun addLink(
        id : Long,
        addLinkRequest : AddLinkRequest
    ) : Result<Unit>

    suspend fun deleteFolder(
        id : Long
    ) : Result<Unit>

    suspend fun deleteLink(
        id : Long,
        articleId : Long
    ) : Result<Unit>

}