package com.linkedlist.linkllet.core.data.repository.fake

import com.linkedlist.linkllet.core.data.model.Folder
import com.linkedlist.linkllet.core.data.model.Link
import com.linkedlist.linkllet.core.data.repository.LinkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLinkRepository : LinkRepository {
    var id = 1L
    private val folders = mutableListOf<Folder>()

    override fun addFolder(name: String): Flow<Unit> = flow {
        folders.add(
            Folder(
                id = id++,
                name = name,
                type = "DEFAULT",
                size = 0
            )
        )

        emit(Unit)
    }

    override fun getFolders(): Flow<List<Folder>> =
        flow {
            emit(folders)
        }

    override fun getLinks(id: Long): Flow<List<Link>> {
        TODO("Not yet implemented")
    }

    override fun search(query: String): Flow<List<Link>> {
        TODO("Not yet implemented")
    }

    override fun addLink(id: Long, name: String, url: String): Flow<Unit> {
        TODO("Not yet implemented")
    }

    override fun deleteFolder(id: Long): Flow<Unit> {
        TODO("Not yet implemented")
    }

    override fun deleteLink(id: Long, articleId: Long): Flow<Unit> {
        TODO("Not yet implemented")
    }
}