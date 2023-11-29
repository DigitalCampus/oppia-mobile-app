package org.digitalcampus.oppiamobile.data.course.remote

import com.google.gson.annotations.SerializedName

data class QuizAttemptResponse(
    @SerializedName("badges")
    val badges: Int,
    @SerializedName("points")
    val points: Int,)
