package org.digitalcampus.oppiamobile.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

enum class FormFieldType {
    @SerialName("text")
    TEXT,

    @SerialName("number")
    NUMBER,

    @SerialName("phone")
    PHONE,

    @SerialName("email")
    EMAIL,

    @SerialName("password")
    PASSWORD,
}

@Serializable
data class FormField(
    val name: String,
    val label: String,
    val required: Boolean,
    @SerialName("min_size") val minSize: Int? = null,
    @SerialName("max_size") val maxSize: Int? = null,
    @SerialName("validation_regex") val validationRegex: String? = null,
    @SerialName("helper_text") val helperText: String? = null,
    val type: FormFieldType,
    @SerialName("validation_rules") val validationRules: List<String>? = null,
    @SerialName("should_match") val shouldMatch: String? = null,
    var value: String? = null,
)
