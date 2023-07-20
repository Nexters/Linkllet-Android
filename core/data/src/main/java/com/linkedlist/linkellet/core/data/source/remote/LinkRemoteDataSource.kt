package com.linkedlist.linkellet.core.data.source.remote

import com.linkedlist.linkellet.core.data.model.Folder
import com.linkedlist.linkellet.core.data.model.Link
import com.linkedlist.linkellet.core.data.model.request.AddLinkRequest

interface LinkRemoteDataSource {

    suspend fun getFolders() : Result<List<Folder>>

    suspend fun getLinks(id : Long) : Result<List<Link>>

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