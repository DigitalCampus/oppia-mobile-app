package org.digitalcampus.oppiamobile.data.user.db.repository

import javax.inject.Inject

class UserRepository @Inject constructor(
    val authDbDataSource: UserDbDataSource,
    val authRemoteDataSource: AuthRemoteDataSource
) {

    suspend fun login(username: String, password: String) {
        val response = authRemoteDataSource.login(username, password)
    }
}