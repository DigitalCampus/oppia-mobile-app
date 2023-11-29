package org.digitalcampus.oppiamobile.data.course.remote


import com.google.gson.annotations.SerializedName
import org.digitalcampus.oppiamobile.data.config.remote.commonResponseData.Meta

data class TagsResponse(
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("tags")
    val tags: List<TagItem>?
)



data class TagItem(
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
    val id: Int
)




