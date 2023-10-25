package org.digitalcampus.oppiamobile.domain.useCases

class CheckFieldNotEmpty {

    fun check(text: String?) = text?.isNotBlank() ?: false
}
