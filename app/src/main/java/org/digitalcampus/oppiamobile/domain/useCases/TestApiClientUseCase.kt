package org.digitalcampus.oppiamobile.domain.useCases

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.digitalcampus.oppiamobile.data.course.remote.CourseRemoteService
import org.digitalcampus.oppiamobile.data.course.remote.QuizAttemptRequest
import org.digitalcampus.oppiamobile.data.gamification.remote.GamificationRemoteService
import org.digitalcampus.oppiamobile.data.user.remote.profile.DeleteAccountRequest
import org.digitalcampus.oppiamobile.data.user.remote.profile.DownloadDataType
import org.digitalcampus.oppiamobile.data.user.remote.profile.ProfileRemoteService
import org.digitalcampus.oppiamobile.di.ApiKey
import javax.inject.Inject


class TestApiClientUseCase @Inject constructor(
    private val remoteService: GamificationRemoteService,
    @ApiKey private val apiKey: String,
) {

    suspend operator fun invoke() {

        try {

            val response = remoteService.getAwards(apiKey)

            GsonBuilder().apply {
                setPrettyPrinting()
                create().run {
                    Log.d("TestApiClientUseCase", toJson(response))
                }
            }

        } catch (e: Exception) {
            Log.e("TestApiClientUseCase", e.message ?: "null" )
        }

    }
}