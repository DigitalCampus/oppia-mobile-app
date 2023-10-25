package org.digitalcampus.oppiamobile.data.course.remote


import com.google.gson.annotations.SerializedName


data class TagResponse(
    @SerializedName("courses")
    val courses: List<CoursesItem>?,
    @SerializedName("count")
    val count: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("id")
    val id: String = ""
)


data class CoursesItem(
    @SerializedName("author")
    val author: String?,
    @SerializedName("resource_uri")
    val resourceUri: String,
    @SerializedName("description")
    val description: Map<String, String>,
    @SerializedName("organisation")
    val organisation: String?,
    @SerializedName("title")
    val title: Map<String, String>,
    @SerializedName("priority")
    val priority: Int,
    @SerializedName("version")
    val version: Long,
    @SerializedName("shortname")
    val shortname: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("restricted")
    val restricted: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("status")
    val status: String?,
    @SerializedName("username")
    val username: String?
)



