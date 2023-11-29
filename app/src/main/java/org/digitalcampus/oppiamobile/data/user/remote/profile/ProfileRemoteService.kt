package org.digitalcampus.oppiamobile.data.user.remote.profile

import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ProfileRemoteService {

    @GET("profile/")
    suspend fun getProfile(
        @Header("Authorization") apiKey: String,
    ): ProfileResponse

    @GET("cohorts/")
    suspend fun getCohorts(
        @Header("Authorization") apiKey: String,
    ): List<Int>

    @POST("profileupdate/")
    @JvmSuppressWildcards
    suspend fun updateProfile(
        @Header("Authorization") apiKey: String,
        @Body fields: Map<String, Any?>,
    ): ResponseBody

    @POST("downloaddata/{type}/")
    suspend fun downloadAccountData(
        @Header("Authorization") apiKey: String,
        @Path("type") type: String
    ): ResponseBody

    @POST("deleteaccount/")
    suspend fun deleteAccount(
        @Header("Authorization") apiKey: String,
        @Body deleteAccountRequest: DeleteAccountRequest,
    ): ResponseBody

}
