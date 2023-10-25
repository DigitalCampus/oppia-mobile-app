package org.digitalcampus.oppiamobile.data.user.remote.auth

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthRemoteService {

    @POST("user/")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("register/")
    suspend fun register(@Body loginRequest: LoginRequest): LoginResponse
}
