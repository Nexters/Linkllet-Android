package com.linkedlist.linkellet.core.data.source.remote.api

import com.linkedlist.linkellet.core.data.model.request.AddLinkRequest
import com.linkedlist.linkellet.core.data.model.response.FolderResponse
import com.linkedlist.linkellet.core.data.model.response.LinkResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LinkService {

    @GET("/api/v1/folders")
    suspend fun getFolders() : Response<FolderResponse>

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