package org.digitalcampus.oppiamobile.domain.use_cases

class CheckFieldNotEmpty {

    fun check(text: String?) = text?.isNotBlank() ?: false
}