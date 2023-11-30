package org.digitalcampus.oppiamobile.data.gamification.remote


import com.google.gson.annotations.SerializedName

data class LeaderboardResponse(
    @SerializedName("server")
    val server: String,
    @SerializedName("leaderboard")
    val leaderboard: List<LeaderboardItem>,
    @SerializedName("generated_date")
    val generatedDate: String
)


data class LeaderboardItem(
    @SerializedName("badges")
    val badges: Int,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("position")
    val position: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("points")
    val points: Int
)


