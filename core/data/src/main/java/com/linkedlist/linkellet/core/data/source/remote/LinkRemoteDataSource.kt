package com.linkedlist.linkellet.core.data.source.remote

import com.linkedlist.linkellet.core.data.model.Folder
import com.linkedlist.linkellet.core.data.model.request.AddLinkRequest

interface LinkRemoteDataSource {

    suspend fun getFolders() : Result<List<Folder>>

    suspend fun addLink(
        id : Long,
        addLinkRequest : AddLinkRequest
    ) : Result<Unit>

}