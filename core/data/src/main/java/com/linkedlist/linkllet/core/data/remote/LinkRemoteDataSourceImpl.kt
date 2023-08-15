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

    override suspend fun addFolder(name: String): Result<Unit> =
        runCatching {
            val response = linkService.addFolder(folder = AddFolderRequest(name = name))
            if (!response.isSuccessful) throw RuntimeException(
                response.errorBody().toMessage("폴더 저장에 실패했어요.")
            )
        }

    override suspend fun getFolders(): Result<List<Folder>> =
        runCatching {
            val response = linkService.getFolders()
            if (!response.isSuccessful) throw RuntimeException(
                response.errorBody().toMessage("폴더 조회에 실패했어요.")
            )
            response.body()?.folderList ?: emptyList()
        }

    override suspend fun getLinks(id: Long): Result<List<Link>> =
        runCatching {
            val response = linkService.getLinks(id)
            if (!response.isSuccessful) throw RuntimeException(
                response.errorBody().toMessage("링크 조회에 실패했어요.")
            )
            response.body()?.articleList ?: emptyList()
        }

    override suspend fun search(query: String): Result<List<Link>> = runCatching {
        val response = linkService.search(content = query)
        if(!response.isSuccessful) throw RuntimeException(response.errorBody().toMessage("검색에 실패했어요."))
        response.body()?.articleList ?: emptyList()
    }

    override suspend fun addLink(
        id: Long,
        addLinkRequest: AddLinkRequest
    ): Result<Unit> = runCatching {
        val response = linkService.addLink(id = id, addLinkRequest = addLinkRequest)
        if (!response.isSuccessful) throw RuntimeException(
            response.errorBody().toMessage("링크를 저장할 수 없어요.")
        )
    }

    override suspend fun deleteFolder(id: Long): Result<Unit> =
        runCatching {
            val response = linkService.deleteFolder(id = id)
            if (!response.isSuccessful) throw RuntimeException(
                response.errorBody().toMessage("폴더를 삭제할 수 없어요.")
            )
        }

    override suspend fun deleteLink(id: Long, articleId: Long): Result<Unit> =
        runCatching {
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