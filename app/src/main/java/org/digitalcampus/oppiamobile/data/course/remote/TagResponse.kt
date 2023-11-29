package org.digitalcampus.oppiamobile.data.course.remote


import com.google.gson.annotations.SerializedName
import org.digitalcampus.oppiamobile.data.course.remote.common.CourseItem


data class TagResponse(
    @SerializedName("courses")
    val courses: List<CourseItem>?,
    @SerializedName("count")
    val count: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("id")
    val id: String = ""
)





