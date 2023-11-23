package org.digitalcampus.oppiamobile.domain.useCases

import android.util.Log
import org.digitalcampus.oppiamobile.data.user.repository.UserRepository
import org.digitalcampus.oppiamobile.domain.model.User
import javax.inject.Inject

class UserRegisterUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(user: User): User {
        userRepository.register(user)
        Log.d("UserRegisterUseCase", "doRegister: User: $user")
        return user
    }
}
