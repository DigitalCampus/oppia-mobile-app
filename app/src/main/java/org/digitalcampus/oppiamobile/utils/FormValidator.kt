package org.digitalcampus.oppiamobile.utils

import org.digitalcampus.oppiamobile.domain.model.FormField
import org.digitalcampus.oppiamobile.domain.model.FormFieldType

class FormValidator {

    companion object {
        const val EMAIL_REGEX = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}"
    }

    fun validateFormField(field: FormField): String {
        return validateCondition(field.required) { validateRequired(field.value) }
            ?: validateCondition(field.minSize != null) { validateMinLength(field.value, field.minSize) }
            ?: validateCondition(field.maxSize != null) { validateMaxLength(field.value, field.maxSize) }
            ?: validateCondition(field.type == FormFieldType.EMAIL) { validateEmail(field.value) }
            // Add more conditions for other field types if needed
            ?: ""
    }

    private inline fun validateCondition(condition: Boolean, validation: () -> String?): String? {
        return if (condition) validation() else null
    }

    private fun validateRequired(value: String?): String? {
        return if (value.isNullOrBlank()) "This field is required" else null
    }

    private fun validateMinLength(value: String?, minSize: Int?): String? {
        return if (value.isNullOrBlank() || value.length < minSize!!) "This field should be longer than $minSize" else null
    }

    private fun validateMaxLength(value: String?, maxSize: Int?): String? {
        return if (value?.length!! > maxSize!!) "This field exceeds max size of $maxSize" else null
    }

    private fun validateEmail(email: String?): String? {
        val emailValid = email?.matches(Regex(EMAIL_REGEX)) ?: false

        return if (!emailValid) "The email is not valid" else null
    }

    fun validateMatch(value1: String?, value2: String?): String {
        return if (value1 != value2) "The values does not match" else ""
    }
}
