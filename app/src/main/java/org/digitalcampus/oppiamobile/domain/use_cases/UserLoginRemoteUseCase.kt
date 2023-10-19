package org.digitalcampus.oppiamobile.domain.use_cases

import android.util.Log
import org.digitalcampus.oppiamobile.data.user.repository.UserRepository
import org.digitalcampus.oppiamobile.domain.model.User
import javax.inject.Inject

class UserLoginRemoteUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(username: String, password: String): User {

        val user = userRepository.login(username, password)
        Log.d("UserLoginRemoteUseCase", "doLogin: User: $user")
        return user

    }
}