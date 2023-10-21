package com.linkedlist.linkllet.core.data.remote

import android.util.Log
import com.linkedlist.linkllet.core.data.model.Folder
import com.linkedlist.linkllet.core.data.model.Link
import com.linkedlist.linkllet.core.data.model.request.AddFolderRequest
import com.linkedlist.linkllet.core.data.model.request.AddLinkRequest
import com.linkedlist.linkllet.core.data.remote.api.LinkService
import okhttp3.ResponseBody
import org.json.JSONObject
import javax.inject.Inject

class LinkRemoteDataSourceImpl @Inject constructor(
    private val linkService: LinkService
) : LinkRemoteDataSource {

    override suspend fun addFolder(name: String) {
        val response = linkService.addFolder(folder = AddFolderRequest(name = name))
        if (!response.isSuccessful) throw RuntimeException(
            response.errorBody().toMessage("폴더 저장에 실패했어요.")
        )
    }

    override suspend fun getFolders(): List<Folder> {
        val errorMessage = "폴더 조회에 실패했어요."

        val response = linkService.getFolders()
        if (!response.isSuccessful) throw RuntimeException(
            response.errorBody().toMessage(errorMessage)
        )
        return response.body()?.folderList ?: throw RuntimeException(errorMessage)
    }

    override suspend fun getLinks(id: Long): List<Link> {
        val errorMessage = "링크 조회에 실패했어요."

        val response = linkService.getLinks(id)
        if (!response.isSuccessful) throw RuntimeException(
            response.errorBody().toMessage(errorMessage)
        )
        return response.body()?.articleList ?: throw RuntimeException(errorMessage)
    }

    override suspend fun search(query: String): List<Link> {
        val errorMessage = "검색에 실패했어요."

        val response = linkService.search(content = query)
        if (!response.isSuccessful) throw RuntimeException(
            response.errorBody().toMessage(errorMessage)
        )
        return response.body()?.articleList ?: throw RuntimeException(errorMessage)
    }

    override suspend fun addLink(
        id: Long,
        addLinkRequest: AddLinkRequest
    ) {
        val response = linkService.addLink(id = id, addLinkRequest = addLinkRequest)
        if (!response.isSuccessful) throw RuntimeException(
            response.errorBody().toMessage("링크를 저장할 수 없어요.")
        )
    }

    override suspend fun deleteFolder(id: Long) {
        val response = linkService.deleteFolder(id = id)
        if (!response.isSuccessful) throw RuntimeException(
            response.errorBody().toMessage("폴더를 삭제할 수 없어요.")
        )
    }

    override suspend fun deleteLink(id: Long, articleId: Long) {
        val response = linkService.deleteLink(id = id, articleId = articleId)
        if (!response.isSuccessful) throw RuntimeException(
            response.errorBody().toMessage("링크를 삭제할 수 없어요.")
        )
    }

    private fun ResponseBody?.toMessage(defaultMessage: String): String {
        return try {
            JSONObject(this?.string()!!).getString("message")
        } catch (e: Exception) {
            Log.e("errorBodyError", e.toString())
            defaultMessage
        }
    }

}