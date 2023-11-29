package org.digitalcampus.oppiamobile.data.user.remote.profile


import com.google.gson.annotations.SerializedName


data class ProfileResponse(
    @SerializedName("metadata")
    val metadata: Metadata,
    @SerializedName("scoring")
    val scoring: Boolean,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("organisation")
    val organisation: String?,
    @SerializedName("points")
    val points: Int,
    @SerializedName("badges")
    val badges: Int,
    @SerializedName("badging")
    val badging: Boolean,
    @SerializedName("course_points")
    val coursePoints: Int?,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("job_title")
    val jobTitle: String?,
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("cohorts")
    val cohorts: List<Int>,
)


data class Metadata(
    @SerializedName("NETWORK")
    val network: Boolean?,
    @SerializedName("NETWORK_CONNECTED")
    val networkConnected: Boolean?,
    @SerializedName("WIFI_ON")
    val wifiOn: Boolean?,
    @SerializedName("BATTERY_LEVEL")
    val batteryLevel: Boolean?,
    @SerializedName("DEVICE_ID")
    val deviceId: Boolean?,
    @SerializedName("SIM_SERIAL")
    val simSerial: Boolean? = false
)
