package com.linkedlist.linkllet.core.data.repository

import com.linkedlist.linkllet.core.data.model.Folder
import com.linkedlist.linkllet.core.data.model.Link
import kotlinx.coroutines.flow.Flow

interface LinkRepository {

    fun addFolder(name: String): Flow<Unit>

    fun getFolders(): Flow<List<Folder>>

    fun getLinks(id: Long): Flow<List<Link>>

    fun search(query: String): Flow<List<Link>>

    fun addLink(
        id: Long,
        name: String,
        url: String
    ): Flow<Unit>

    fun deleteFolder(
        id: Long
    ): Flow<Unit>

    fun deleteLink(
        id: Long,
        articleId: Long
    ): Flow<Unit>
}