package org.digitalcampus.oppiamobile.data.auth.repository

import javax.inject.Inject

class AuthRepository @Inject constructor(
    val authDbDataSource: AuthDbDataSource,
    val authRemoteDataSource: AuthRemoteDataSource
) {

    suspend fun login(username: String, password: String) {
        val response = authRemoteDataSource.login(username, password)
    }
}