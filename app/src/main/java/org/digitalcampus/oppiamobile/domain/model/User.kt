package org.digitalcampus.oppiamobile.domain.model

data class User(
    val email: String,
    val username: String,
    val firstName: String,
    val lastName: String,
    var apiKey: String? = null,
    val password: String,
    val isOfflineRegister: Boolean,
    val userCustomFields: Map<String, CustomValue> = HashMap(),
    var points: Int? = null,
) {
    companion object {

        // Include here all the User's properties that are required to register a new User
        val registerRequiredProperties: List<String> by lazy {
            listOf(
                User::email.name,
                User::username.name,
                User::firstName.name,
                User::lastName.name,
                User::password.name,
            )
        }
    }
}
