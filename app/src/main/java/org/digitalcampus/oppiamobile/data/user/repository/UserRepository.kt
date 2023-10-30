package org.digitalcampus.oppiamobile.data.user.repository

import org.digitalcampus.oppiamobile.data.user.db.entity.passwordEncrypted
import org.digitalcampus.oppiamobile.domain.model.User
import org.digitalcampus.oppiamobile.utils.ConnectivityUtils
import org.digitalcampus.oppiamobile.utils.CryptoUtils
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val authDbDataSource: UserDbDataSource, // TODO COMENTAR: prefijo auth o user?
    private val userRemoteDataSource: UserRemoteDataSource,
    private val connectivityUtils: ConnectivityUtils
) {

    suspend fun login(username: String, password: String): User {
        val isConnected = connectivityUtils.isConnected()
        return if (isConnected) {
            loginRemotely(username, password)
        } else {
            loginLocaly(username, password)
        }
    }

    private suspend fun loginRemotely(username: String, password: String): User {
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

    private suspend fun loginLocaly(username: String, password: String) : User {
        val localUser = authDbDataSource.getByUsername(username)
        if (localUser == null) {
            throw Exception("User not found")
        } else {
            if (localUser.passwordEncrypted == CryptoUtils.encryptLocalPassword(password)) {
                // TODO pending user api key check
                return localUser.toUser()
            } else {
                throw Exception("Wrong password")
            }
        }
    }
}
