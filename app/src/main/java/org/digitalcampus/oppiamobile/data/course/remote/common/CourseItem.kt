package org.digitalcampus.oppiamobile.data.course.remote.common

import com.google.gson.annotations.SerializedName

data class CourseItem(
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