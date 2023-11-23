package org.digitalcampus.oppiamobile.data.user.remote.auth

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("password") val password: String,
    @SerializedName("passwordagain") val passwordAgain: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("email") val email: String,
    @SerializedName("username") val username: String,
)
