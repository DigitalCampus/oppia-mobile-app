package org.digitalcampus.oppiamobile.domain.model

data class User(
    val email: String,
    val username: String,
    val firstName: String,
    val lastName: String,
    val apiKey: String,
)
