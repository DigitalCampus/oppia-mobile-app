package org.digitalcampus.oppiamobile.data.course.remote


import com.google.gson.annotations.SerializedName


data class QuizAttemptRequest(
    @SerializedName("score")
    val score: Double,
    @SerializedName("quiz_id")
    val quizId: Int,
    @SerializedName("instance_id")
    val instanceId: String,
    @SerializedName("timetaken")
    val timetaken: Int,
    @SerializedName("responses")
    val responses: List<ResponsesItem>,
    @SerializedName("maxscore")
    val maxscore: Double,
    @SerializedName("event")
    val event: String,
    @SerializedName("attempt_date")
    val attemptDate: String,
    @SerializedName("points")
    val points: Int
)


data class ResponsesItem(
    @SerializedName("score")
    val score: Double,
    @SerializedName("text")
    val text: String,
    @SerializedName("question_id")
    val questionId: Int
)

