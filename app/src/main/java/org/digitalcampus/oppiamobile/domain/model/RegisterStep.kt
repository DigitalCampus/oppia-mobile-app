package org.digitalcampus.oppiamobile.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterStep(
    val order: Int,
    val title: String,
    val description: String,
    val fields: List<FormField>,
)
