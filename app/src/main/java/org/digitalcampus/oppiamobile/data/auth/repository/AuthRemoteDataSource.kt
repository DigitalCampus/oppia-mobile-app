package org.digitalcampus.oppiamobile.data.auth.repository

import org.digitalcampus.oppiamobile.data.auth.db.UserDao
import org.digitalcampus.oppiamobile.data.auth.remote.AuthRemoteService
import org.digitalcampus.oppiamobile.data.auth.remote.LoginRequest
import org.digitalcampus.oppiamobile.domain.model.User
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val authRemoteService: AuthRemoteService) {

    suspend fun login(username: String, password: String): User {
        val response = authRemoteService.login(LoginRequest(username, password))
        return User(
            response.email,
            response.username,
            response.firstName,
            response.lastName,
            response.organisation,
            response.jobTitle,
            response.apiKey
        )
    }
}