package com.linkedlist.linkllet.core.data.repository.fake

import com.linkedlist.linkllet.core.data.model.Folder
import com.linkedlist.linkllet.core.data.model.Link
import com.linkedlist.linkllet.core.data.repository.LinkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLinkRepository : LinkRepository {
    var id = 1L
    private val folders = mutableListOf<Folder>()

    override suspend fun addFolder(name: String): Flow<Result<Unit>> =
        flow {
            val res = runCatching {
                folders.add(
                    Folder(
                        id = id++,
                        name = name,
                        type = "DEFAULT",
                        size = 0
                    )
                )
                Unit
            }

            emit(res)
        }

    override suspend fun getFolders(): Flow<Result<List<Folder>>> =
        flow {
            emit(Result.success(folders))
        }

    override suspend fun getLinks(id: Long): Flow<Result<List<Link>>> {
        TODO("Not yet implemented")
    }

    override suspend fun addLink(id: Long, name: String, url: String): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFolder(id: Long): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteLink(id: Long, articleId: Long): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }
}