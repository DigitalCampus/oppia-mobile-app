package org.digitalcampus.oppiamobile.data.auth.remote

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthRemoteService {

    @POST("user/")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
}