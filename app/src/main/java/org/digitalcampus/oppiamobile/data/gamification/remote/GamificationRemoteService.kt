package org.digitalcampus.oppiamobile.data.gamification.remote

import retrofit2.http.GET
import retrofit2.http.Header

interface GamificationRemoteService {

    @GET("leaderboard/")
    suspend fun getLeaderboard(@Header("Authorization") apiKey: String): LeaderboardResponse

    @GET("awards/")
    suspend fun getAwards(@Header("Authorization") apiKey: String): AwardsResponse

}