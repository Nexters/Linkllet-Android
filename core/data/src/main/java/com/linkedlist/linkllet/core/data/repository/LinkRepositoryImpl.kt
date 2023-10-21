package com.linkedlist.linkllet.core.data.repository

import com.linkedlist.linkllet.core.data.model.Folder
import com.linkedlist.linkllet.core.data.model.Link
import com.linkedlist.linkllet.core.data.model.request.AddLinkRequest
import com.linkedlist.linkllet.core.data.remote.LinkRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LinkRepositoryImpl @Inject constructor(
    private val linkRemoteDataSource: LinkRemoteDataSource
) : LinkRepository {
    override fun addFolder(name: String): Flow<Unit> = flow {
        emit(linkRemoteDataSource.addFolder(name = name))
    }

    override fun getFolders(): Flow<List<Folder>> = flow {
        emit(linkRemoteDataSource.getFolders())
    }

    override fun getLinks(id: Long): Flow<List<Link>> = flow {
        emit(linkRemoteDataSource.getLinks(id))
    }

    override fun search(query: String): Flow<List<Link>> = flow {
        emit(linkRemoteDataSource.search(query))
    }

    override fun addLink(id: Long, name: String, url: String): Flow<Unit> = flow {
        emit(
            linkRemoteDataSource.addLink(
                id = id,
                addLinkRequest = AddLinkRequest(
                    name = name,
                    url = url
                )
            )
        )
    }

    override fun deleteFolder(id: Long): Flow<Unit> = flow {
        emit(linkRemoteDataSource.deleteFolder(id = id))
    }

    override fun deleteLink(id: Long, articleId: Long): Flow<Unit> = flow {
        emit(
            linkRemoteDataSource.deleteLink(
                id = id,
                articleId = articleId
            )
        )
    }
}