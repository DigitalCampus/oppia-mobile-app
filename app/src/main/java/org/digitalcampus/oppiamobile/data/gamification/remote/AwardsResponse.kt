package org.digitalcampus.oppiamobile.data.gamification.remote


import com.google.gson.annotations.SerializedName


data class AwardsResponse(
    @SerializedName("objects")
    val objects: List<AwardItem>
)


data class AwardItem(
    @SerializedName("validation_uuid")
    val validationUuid: String,
    @SerializedName("badge")
    val badge: BadgeItem,
    @SerializedName("emailed")
    val emailed: Boolean,
    @SerializedName("certificate_pdf")
    val certificatePdf: String?,
    @SerializedName("description")
    val description: String,
    @SerializedName("award_date")
    val awardDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("badge_icon")
    val badgeIcon: String
)


data class BadgeItem(
    @SerializedName("ref")
    val ref: String = "",
    @SerializedName("allow_multiple_awards")
    val allowMultipleAwards: Boolean = false,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("default_icon")
    val defaultIcon: String = "",
    @SerializedName("points")
    val points: Int = 0
)


