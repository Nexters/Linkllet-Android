package com.linkedlist.linkllet.core.data.source.remote

import android.util.Log
import com.linkedlist.linkllet.core.data.model.Folder
import com.linkedlist.linkllet.core.data.model.Link
import com.linkedlist.linkllet.core.data.model.request.AddFolderRequest
import com.linkedlist.linkllet.core.data.model.request.AddLinkRequest
import com.linkedlist.linkllet.core.data.source.remote.api.LinkService
import okhttp3.ResponseBody
import org.json.JSONObject
import javax.inject.Inject

class LinkRemoteDataSourceImpl @Inject constructor(
    private val linkService: LinkService
) : LinkRemoteDataSource {

    override suspend fun addFolder(name: String): Result<Unit> =
        linkService.addFolder(folder = AddFolderRequest(name = name)).runCatching {
            if (!isSuccessful) throw RuntimeException(errorBody().toMessage("폴더 저장에 실패했어요."))
        }

    override suspend fun getFolders(): Result<List<Folder>> =
        linkService.getFolders().runCatching {
            if (!isSuccessful) throw RuntimeException(errorBody().toMessage("폴더 조회에 실패했어요."))
            body()?.folderList ?: emptyList()
        }

    override suspend fun getLinks(id: Long): Result<List<Link>> =
        linkService.getLinks(id).runCatching {
            if (!isSuccessful) throw RuntimeException(errorBody().toMessage("링크 조회에 실패했어요."))
            body()?.articleList ?: emptyList()
        }

    override suspend fun addLink(
        id: Long,
        addLinkRequest: AddLinkRequest
    ): Result<Unit> = linkService.addLink(id = id, addLinkRequest = addLinkRequest).runCatching {
        if (!isSuccessful) throw RuntimeException(errorBody().toMessage("링크를 저장할 수 없어요."))
    }

    override suspend fun deleteFolder(id: Long): Result<Unit> =
        linkService.deleteFolder(id = id).runCatching {
            if (!isSuccessful) throw RuntimeException(errorBody().toMessage("폴더를 삭제할 수 없어요."))
        }

    override suspend fun deleteLink(id: Long, articleId: Long): Result<Unit> =
        linkService.deleteLink(id = id, articleId = articleId).runCatching {
            if (!isSuccessful) throw RuntimeException(errorBody().toMessage("링크를 삭제할 수 없어요."))
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