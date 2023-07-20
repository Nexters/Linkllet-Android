package com.linkedlist.linkellet.core.data.repository

import com.linkedlist.linkellet.core.data.model.Folder
import com.linkedlist.linkellet.core.data.model.Link
import kotlinx.coroutines.flow.Flow

interface LinkRepository {

    suspend fun getFolders() : Flow<Result<List<Folder>>>

    suspend fun getLinks(id : Long) : Flow<Result<List<Link>>>

    suspend fun addLink(
        id : Long,
        name : String,
        url : String
    ) : Flow<Result<Unit>>

    suspend fun deleteFolder(
        id : Long
    ) : Flow<Result<Unit>>

    suspend fun deleteLink(
        id : Long,
        articleId : Long
    ) : Flow<Result<Unit>>
}