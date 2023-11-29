package org.digitalcampus.oppiamobile.data.course.remote


import com.google.gson.annotations.SerializedName
import org.digitalcampus.oppiamobile.data.config.remote.commonResponseData.Meta
import org.digitalcampus.oppiamobile.data.course.remote.common.CourseItem

data class CoursesResponse(
    @SerializedName("courses")
    val courses: List<CourseItem>,
    @SerializedName("meta")
    val meta: Meta
)



