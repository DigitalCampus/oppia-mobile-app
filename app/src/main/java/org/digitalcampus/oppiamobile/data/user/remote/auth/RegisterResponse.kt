package org.digitalcampus.oppiamobile.data.user.remote.auth

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("api_key") val apiKey: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("email") val email: String,
    @SerializedName("points") val points: Int,
    @SerializedName("username") val username: String
)