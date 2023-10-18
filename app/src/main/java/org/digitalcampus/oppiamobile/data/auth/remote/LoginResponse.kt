package org.digitalcampus.oppiamobile.data.auth.remote

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("organisation")
    val organisation: String,
    @SerializedName("job_title")
    val jobTitle: String,
    @SerializedName("points")
    val points: Int,
    @SerializedName("badges")
    val badges: Int,
    @SerializedName("badging")
    val badging: Boolean,
    @SerializedName("api_key")
    val apiKey: String,
)