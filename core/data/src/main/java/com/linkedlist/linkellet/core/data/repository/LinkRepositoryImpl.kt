package com.linkedlist.linkellet.core.data.repository

import com.linkedlist.linkellet.core.data.model.Folder
import com.linkedlist.linkellet.core.data.model.Link
import com.linkedlist.linkellet.core.data.model.request.AddLinkRequest
import com.linkedlist.linkellet.core.data.source.remote.LinkRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LinkRepositoryImpl @Inject constructor(
    private val linkRemoteDataSource: LinkRemoteDataSource
) : LinkRepository {
    override suspend fun addFolder(name: String): Flow<Result<Unit>> = flow {
        linkRemoteDataSource.addFolder(name = name)
            .onSuccess {
                emit(Result.success(Unit))
            }.onFailure {
                emit(Result.failure(it))
            }
    }

    override suspend fun getFolders(): Flow<Result<List<Folder>>> = flow {
        linkRemoteDataSource.getFolders()
        .onSuccess {
            emit(Result.success(it))
        }.onFailure {
            emit(Result.failure(it))
        }
    }

    override suspend fun getLinks(id: Long): Flow<Result<List<Link>>> = flow {
        linkRemoteDataSource.getLinks(id)
            .onSuccess {
                emit(Result.success(it))
            }.onFailure {
                emit(Result.failure(it))
            }
    }

    override suspend fun addLink(id: Long, name: String, url: String): Flow<Result<Unit>> = flow {
        linkRemoteDataSource.addLink(
            id = id,
            addLinkRequest = AddLinkRequest(
                name = name,
                url = url
            )
        ).onSuccess {
            emit(Result.success(Unit))
        }.onFailure {
            emit(Result.failure(it))
        }
    }

    override suspend fun deleteFolder(id: Long): Flow<Result<Unit>> = flow {
        linkRemoteDataSource.deleteFolder(
            id = id
        ).onSuccess {
            emit(Result.success(Unit))
        }.onFailure {
            emit(Result.failure(it))
        }
    }

    override suspend fun deleteLink(id: Long, articleId: Long): Flow<Result<Unit>> = flow {
        linkRemoteDataSource.deleteLink(
            id = id,
            articleId = articleId
        ).onSuccess {
            emit(Result.success(Unit))
        }.onFailure {
            emit(Result.failure(it))
        }
    }
}