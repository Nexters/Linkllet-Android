package com.linkedlist.linkellet.core.data.source.remote

import com.linkedlist.linkellet.core.data.model.Folder
import com.linkedlist.linkellet.core.data.model.Link
import com.linkedlist.linkellet.core.data.model.request.AddFolderRequest
import com.linkedlist.linkellet.core.data.model.request.AddLinkRequest
import com.linkedlist.linkellet.core.data.source.remote.api.LinkService
import java.lang.RuntimeException
import javax.inject.Inject

class LinkRemoteDataSourceImpl @Inject constructor(
    private val linkService: LinkService
) : LinkRemoteDataSource {

    override suspend fun addFolder(name: String): Result<Unit> {
        return try {
            val response = linkService.addFolder(folder = AddFolderRequest(name = name))
            if(response.isSuccessful) Result.success(Unit)
            else Result.failure(RuntimeException())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

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

    override suspend fun getLinks(id: Long): Result<List<Link>> {
        return try {
            val response = linkService.getLinks(id)
            val links = response.body()?.articleList
            if(response.isSuccessful && links != null){
                Result.success(links)
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

    override suspend fun deleteFolder(id: Long): Result<Unit> {
        return try {
            val response = linkService.deleteFolder(
                id = id
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

    override suspend fun deleteLink(id: Long, articleId: Long): Result<Unit> {
        return try {
            val response = linkService.deleteLink(
                id = id,
                articleId = articleId
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