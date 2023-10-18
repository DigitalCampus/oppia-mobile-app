package org.digitalcampus.oppiamobile.domain.model

import com.google.gson.annotations.SerializedName

data class User(
    val email: String,
    val username: String,
    val firstName: String,
    val lastName: String,
    val organisation: String,
    val jobTitle: String,
    val apiKey: String,
)
