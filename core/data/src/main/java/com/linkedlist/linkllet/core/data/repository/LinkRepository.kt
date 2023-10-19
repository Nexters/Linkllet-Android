package com.linkedlist.linkllet.core.data.repository

import com.linkedlist.linkllet.core.data.model.Folder
import com.linkedlist.linkllet.core.data.model.Link
import kotlinx.coroutines.flow.Flow

interface LinkRepository {

    fun addFolder(name: String): Flow<Unit>

    suspend fun getFolders() : Flow<Result<List<Folder>>>

    suspend fun getLinks(id : Long) : Flow<Result<List<Link>>>

    suspend fun search(query: String): Flow<Result<List<Link>>>

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