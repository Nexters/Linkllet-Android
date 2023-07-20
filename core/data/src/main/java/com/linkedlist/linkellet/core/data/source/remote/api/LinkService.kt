package com.linkedlist.linkellet.core.data.source.remote.api

import com.linkedlist.linkellet.core.data.model.request.AddLinkRequest
import com.linkedlist.linkellet.core.data.model.response.FolderResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LinkService {

    @GET("/api/v1/folders")
    suspend fun getFolders() : Response<FolderResponse>

    @POST("/api/v1/folders/{id}/articles")
    suspend fun addLink(
        @Path("id") id : Long ,
        @Body addLinkRequest : AddLinkRequest
    ) : Response<Unit>
}