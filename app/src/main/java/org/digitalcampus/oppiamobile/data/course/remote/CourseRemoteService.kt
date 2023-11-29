package org.digitalcampus.oppiamobile.data.course.remote

import okhttp3.ResponseBody
import org.digitalcampus.oppiamobile.data.course.remote.common.CourseItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Streaming

interface CourseRemoteService {

    @GET("tag/")
    suspend fun getTags(@Header("Authorization") apiKey: String): TagsResponse

    @GET("tag/{id}/")
    suspend fun getTag(@Header("Authorization") apiKey: String, @Path("id") id: Int): TagResponse

    @GET("course/")
    suspend fun getCourses(@Header("Authorization") apiKey: String): CoursesResponse

    @GET("course/{shortname}/")
    suspend fun getCourse(
        @Header("Authorization") apiKey: String,
        @Path("shortname") shortname: String
    ): CourseItem

    @Streaming
    @GET("course/{shortname}/download/")
    suspend fun downloadCourse(
        @Header("Authorization") apiKey: String,
        @Path("shortname") shortname: String
    ): ResponseBody

    @GET("course/{shortname}/activity/")
    suspend fun getCourseActivity(
        @Header("Authorization") apiKey: String,
        @Path("shortname") shortname: String
    ): ResponseBody

    @POST("quizattempt/")
    suspend fun sendQuizAttempt(
        @Header("Authorization") apiKey: String,
        @Body quizAttempt: QuizAttemptRequest
    ): QuizAttemptResponse
}