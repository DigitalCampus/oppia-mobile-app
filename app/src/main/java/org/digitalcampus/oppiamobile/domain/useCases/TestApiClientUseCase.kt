package org.digitalcampus.oppiamobile.domain.useCases

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.digitalcampus.oppiamobile.data.course.remote.CourseRemoteService
import org.digitalcampus.oppiamobile.data.course.remote.QuizAttemptRequest
import org.digitalcampus.oppiamobile.data.user.remote.profile.DeleteAccountRequest
import org.digitalcampus.oppiamobile.data.user.remote.profile.DownloadDataType
import org.digitalcampus.oppiamobile.data.user.remote.profile.ProfileRemoteService
import org.digitalcampus.oppiamobile.di.ApiKey
import javax.inject.Inject


class TestApiClientUseCase @Inject constructor(
    private val remoteService: ProfileRemoteService,
    @ApiKey private val apiKey: String,
) {

    suspend operator fun invoke() {

        try {
//            val json = "{\"quiz_id\":4470,\"attempt_date\":\"2023-11-29 22:02:08\",\"score\":2.3333001136779785,\"maxscore\":5,\"instance_id\":\"02653486-e826-4b8a-bd5b-9f4d54ba17ac\",\"points\":67,\"event\":\"quiz_attempt\",\"responses\":[{\"question_id\":45118,\"score\":0,\"text\":\"Choice in plain text\"},{\"question_id\":45119,\"score\":1,\"text\":\"This is correct as well! And has some HTML into it||This one is correct||\"},{\"question_id\":45120,\"score\":0,\"text\":\"fff\"},{\"question_id\":45121,\"score\":1,\"text\":\"Step AStep BStep CStep D\"},{\"question_id\":45122,\"score\":0.33329999446868896,\"text\":\"Always store the vaccine vials in vaccine carriers at +2°C to +8°C during vaccination||\"}],\"timetaken\":59}"
//            val qa = Gson().fromJson(json, QuizAttemptRequest::class.java)

            val fields: Map<String, Any?> = mapOf(
                "email" to "jbc1@gmail.com",
                "first_name" to "Jjj",
                "last_name" to "Bbb",
                "job_title" to "developer",
                "organisation" to "Triskel"
            )

            val response = remoteService.updateProfile(apiKey, fields)

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