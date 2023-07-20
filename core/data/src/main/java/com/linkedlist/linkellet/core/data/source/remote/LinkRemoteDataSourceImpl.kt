package com.linkedlist.linkellet.core.data.source.remote

import com.linkedlist.linkellet.core.data.model.Auth
import com.linkedlist.linkellet.core.data.model.Folder
import com.linkedlist.linkellet.core.data.model.request.AddLinkRequest
import com.linkedlist.linkellet.core.data.source.remote.api.LinkService
import javax.inject.Inject

class LinkRemoteDataSourceImpl @Inject constructor(
    private val linkService: LinkService
) : LinkRemoteDataSource {

    override suspend fun getFolders(): Result<List<Folder>> {
        return try {
            val response = linkService.getFolders()
            val folders = response.body()?.folderList
            if(response.isSuccessful && folders != null){
                Result.success(folders)
            }else {
                Result.failure(Exception())
            }
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun addLink(
        id : Long,
        addLinkRequest : AddLinkRequest
    ): Result<Unit> {
        return try {
            val response = linkService.addLink(
                id = id,
                addLinkRequest = addLinkRequest
            )
            if(response.isSuccessful){
                Result.success(Unit)
            }else {
                Result.failure(Exception())
            }
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}