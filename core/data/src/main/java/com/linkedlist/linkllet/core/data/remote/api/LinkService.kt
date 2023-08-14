package com.linkedlist.linkllet.core.data.remote.api

import com.linkedlist.linkllet.core.data.model.request.AddFolderRequest
import com.linkedlist.linkllet.core.data.model.request.AddLinkRequest
import com.linkedlist.linkllet.core.data.model.response.FolderResponse
import com.linkedlist.linkllet.core.data.model.response.LinkResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface LinkService {

    @POST("/api/v1/folders")
    suspend fun addFolder(@Body folder: AddFolderRequest): Response<Unit>

    @GET("/api/v1/folders")
    suspend fun getFolders() : Response<FolderResponse>

    @GET("/api/v1/folders/search")
    suspend fun search(
        @Query("content") content: String,
    ): Response<LinkResponse>

    @GET("/api/v1/folders/{id}/articles")
    suspend fun getLinks(
        @Path("id") id : Long
    ) : Response<LinkResponse>

    @POST("/api/v1/folders/{id}/articles")
    suspend fun addLink(
        @Path("id") id : Long ,
        @Body addLinkRequest : AddLinkRequest
    ) : Response<Unit>

    @DELETE("/api/v1/folders/{id}")
    suspend fun deleteFolder(
        @Path("id") id : Long
    ) : Response<Unit>

    @DELETE("/api/v1/folders/{id}/articles/{articleId}")
    suspend fun deleteLink(
        @Path("id") id : Long,
        @Path("articleId") articleId : Long
    ) : Response<Unit>
}