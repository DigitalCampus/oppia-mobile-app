package org.digitalcampus.oppiamobile.domain.use_cases

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.digitalcampus.oppiamobile.data.course.remote.CourseRemoteService
import org.digitalcampus.oppiamobile.di.ApiKey
import javax.inject.Inject


class TestApiClientUseCase @Inject constructor(
    private val courseRemoteService: CourseRemoteService,
    @ApiKey private val apiKey: String,
) {

    suspend operator fun invoke() {

        try {
            val response = courseRemoteService.getTag(apiKey, 113)

            GsonBuilder().apply {
                setPrettyPrinting()
                create().run {
                    Log.d("TestApiClientUseCase", toJson(response))
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}