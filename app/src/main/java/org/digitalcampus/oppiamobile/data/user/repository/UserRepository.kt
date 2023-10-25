package org.digitalcampus.oppiamobile.data.user.repository

import org.digitalcampus.oppiamobile.domain.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val authDbDataSource: UserDbDataSource, // TODO COMENTAR: prefijo auth o user?
    private val userRemoteDataSource: UserRemoteDataSource,
) {

    suspend fun login(username: String, password: String): User {
        val user = userRemoteDataSource.login(username, password)
        val localUser = authDbDataSource.getByUsername(username)
        localUser?.let {
            authDbDataSource.updateUser(
                localUser.copy(
                    first_name = user.firstName,
                    last_name = user.lastName,
                    api_key = user.apiKey,
                ),
            )
        }

        // TODO COMENTAR Si hay que guardar mas cosas como cohorts, custom fields, etc, creamos clase wrapper
        // con varios campos para no devolver aquí el LoginResponse del servidor?

        return user
    }

    suspend fun getLocalUserByUsername(username: String) = authDbDataSource.getByUsername(username)
}
