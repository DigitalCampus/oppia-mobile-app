package org.digitalcampus.oppiamobile.data.course.remote

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CourseRemoteService {

    @GET("tag/")
    suspend fun getTags(@Header("Authorization") apiKey: String): TagsResponse

    @GET("tag/{id}")
    suspend fun getTag(@Header("Authorization") apiKey: String, @Path("id") id: Int): TagResponse
}
