package com.linkedlist.linkellet.core.data.repository

import com.linkedlist.linkellet.core.data.model.Folder
import kotlinx.coroutines.flow.Flow

interface LinkRepository {

    suspend fun getFolders() : Flow<Result<List<Folder>>>

    suspend fun addLink(
        id : Long,
        name : String,
        url : String
    ) : Flow<Result<Unit>>
}