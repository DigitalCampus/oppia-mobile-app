package org.digitalcampus.oppiamobile.data.user.repository

import org.digitalcampus.oppiamobile.data.user.remote.auth.AuthRemoteService
import org.digitalcampus.oppiamobile.data.user.remote.auth.LoginRequest
import org.digitalcampus.oppiamobile.data.user.remote.auth.RegisterRequest
import org.digitalcampus.oppiamobile.domain.model.User
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val authRemoteService: AuthRemoteService) {

    suspend fun login(username: String, password: String): User {
        val response = authRemoteService.login(LoginRequest(username, password))
        return User(
            response.email,
            response.username,
            response.firstName,
            response.lastName,
            response.apiKey,
            password,
            false,
        )
    }

    suspend fun register(user: User): User {
        val response = authRemoteService.register(RegisterRequest(user.password, user.password, user.lastName, user.firstName, user.email, user.username))
        user.points = response.points
        user.apiKey = response.apiKey
        return user
    }
}
