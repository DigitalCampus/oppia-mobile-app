package org.digitalcampus.oppiamobile.domain.use_cases

import org.digitalcampus.oppiamobile.data.user.db.entity.passwordEncrypted
import org.digitalcampus.oppiamobile.data.user.repository.UserRepository
import org.digitalcampus.oppiamobile.data.user.repository.toUser
import org.digitalcampus.oppiamobile.domain.model.User
import org.digitalcampus.oppiamobile.utils.CryptoUtils
import javax.inject.Inject

class UserLoginLocalUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(username: String, password: String): User {
        val userLocal = userRepository.getLocalUserByUsername(username)
        if (userLocal == null) {
            throw Exception("user not found")
        } else {
            if (userLocal.passwordEncrypted == CryptoUtils.encryptLocalPassword(password)) {
                // TODO pending user api key check
                return userLocal.toUser()
            } else {
                throw Exception("Wrong password")
            }
        }

    }
}