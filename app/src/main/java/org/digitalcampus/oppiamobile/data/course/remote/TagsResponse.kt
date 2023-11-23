package org.digitalcampus.oppiamobile.data.course.remote

import com.google.gson.annotations.SerializedName

data class TagsResponse(
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("tags")
    val tags: List<TagsItem>?,
)

data class Meta(
    @SerializedName("next")
    val next: Int?,
    @SerializedName("offset")
    val offset: Int = 0,
    @SerializedName("previous")
    val previous: Int?,
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("limit")
    val limit: Int,
)

data class TagsItem(
    @SerializedName("highlight")
    val highlight: Boolean,
    @SerializedName("order_priority")
    val orderPriority: Int,
    @SerializedName("course_statuses")
    val courseStatuses: Map<String, String>,
    @SerializedName("count_new_downloads_enabled")
    val countNewDownloadsEnabled: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int,
)
