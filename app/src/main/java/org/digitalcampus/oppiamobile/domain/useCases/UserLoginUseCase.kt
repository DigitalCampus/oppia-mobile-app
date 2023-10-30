package org.digitalcampus.oppiamobile.domain.useCases

import android.util.Log
import org.digitalcampus.oppiamobile.data.user.repository.UserRepository
import org.digitalcampus.oppiamobile.domain.model.User
import javax.inject.Inject

class UserLoginUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(username: String, password: String): User {
        val user = userRepository.login(username, password)
        Log.d("UserLoginUseCase", "doLogin: User: $user")
        return user
    }
}
