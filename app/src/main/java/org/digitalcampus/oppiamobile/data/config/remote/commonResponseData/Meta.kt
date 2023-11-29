package org.digitalcampus.oppiamobile.data.config.remote.commonResponseData

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("next")
    val next: String?,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("limit")
    val limit: Int
)