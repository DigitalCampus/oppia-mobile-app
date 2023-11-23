package org.digitalcampus.oppiamobile.ui.auth.register

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.digitalcampus.oppiamobile.data.user.db.dao.CustomFieldDao
import org.digitalcampus.oppiamobile.data.utils.mappers.CustomFieldEntityMapper
import org.digitalcampus.oppiamobile.domain.model.CustomValue
import org.digitalcampus.oppiamobile.domain.model.FormField
import org.digitalcampus.oppiamobile.domain.model.FormFieldType
import org.digitalcampus.oppiamobile.domain.model.IntValue
import org.digitalcampus.oppiamobile.domain.model.RegisterStep
import org.digitalcampus.oppiamobile.domain.model.StringValue
import org.digitalcampus.oppiamobile.domain.model.User
import org.digitalcampus.oppiamobile.domain.useCases.UserLoginUseCase
import org.digitalcampus.oppiamobile.domain.useCases.UserRegisterUseCase
import org.digitalcampus.oppiamobile.ui.common.AppViewModel
import org.digitalcampus.oppiamobile.utils.FormValidator
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRegisterUseCase: UserRegisterUseCase,
    private val userLoginUseCase: UserLoginUseCase,
    private val formValidator: FormValidator,
    private val customFieldDao: CustomFieldDao,
) : AppViewModel() {

    var registerSteps: List<RegisterStep> = emptyList()
        private set
    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    data class UIState(
        val errors: Map<String, String> = HashMap(),
        val registerSuccess: Boolean = false,
        val user: User? = null,
    )

    fun parseJson(jsonString: String) {
        registerSteps = Json.decodeFromString(jsonString)
        validateRequiredFields(registerSteps)
        saveCustomFields(registerSteps)
    }

    private fun validateRequiredFields(registerSteps: List<RegisterStep>) {
        val registerFields = registerSteps.flatMap { it.fields }.map { it.name }
        val requiredProperties = User.registerRequiredProperties
        val missingRequiredFields = requiredProperties.subtract(registerFields.toSet())

        if (missingRequiredFields.isNotEmpty()) {
            val errorMsg = "Field(s) [${missingRequiredFields.joinToString()}] missing in registration form. Make sure you included these fields in registration_form.json file"
            Log.e("RegisterViewModel", errorMsg)
            throw NoSuchElementException(errorMsg)
        }
    }

    private fun saveCustomFields(registerSteps: List<RegisterStep>) {
        val customFields = registerSteps.flatMap { it.fields }
            .filter { field ->
                field.name !in User.registerRequiredProperties &&
                    field.shouldMatch == null
            }

        for (customField in customFields) {
            val customFieldEntity = CustomFieldEntityMapper().mapToEntity(customField)
            viewModelScope.launch {
                customFieldDao.insert(customFieldEntity)
            }
        }
    }

    fun getStepDescription(stepNumber: Int): String {
        return registerSteps[stepNumber].description
    }

    fun getFieldsByStepNumber(stepNumber: Int): List<FormField> {
        return registerSteps[stepNumber].fields
    }

    private fun getAllFormFields(): List<FormField> {
        return registerSteps.flatMap { it.fields }
    }

    fun validateStep(step: Int): Boolean {
        var result = true

        val fields = getFieldsByStepNumber(step)
        for (field in fields) {
            val error = validateField(field, fields)

            _uiState.update { state ->
                state.copy(errors = state.errors + (field.name to error))
            }

            result = result and error.isBlank() // We return false if there is at least one errorAnd
        }

        return result
    }

    private fun validateField(field: FormField, stepFields: List<FormField>): String {
        // Validate individual FormField
        var error = formValidator.validateFormField(field)

        // Validate FormField if should match another FormField. For example in the case of passwords.
        if (error.isEmpty() && field.shouldMatch != null) {
            val shouldMatchField = stepFields.find { it.name == field.shouldMatch }
            error = formValidator.validateMatch(shouldMatchField?.value, field.value)
        }

        return error
    }

    fun registrationCompleted() {
        val formFields = getAllFormFields()

        val user = createUserFromFormFields(formFields)

        viewModelScope.launch {
            userRegisterUseCase.invoke(user)
            // TODO: Update User Profile
            // TODO: Update Gamification
            // TODO: Register tracker
            userLoginUseCase.invoke(user.username, user.password)
            _uiState.update { it.copy(registerSuccess = true, user = user) }
        }
    }

    private fun createUserFromFormFields(formFields: List<FormField>): User {
        val requiredProperties = User.registerRequiredProperties

        val userFieldValues = formFields
            .filter { it.name in requiredProperties }
            .associate { it.name to it.value }

        val userCustomFields = formFields
            .filter { it.name !in requiredProperties }
            .filter { !it.value.isNullOrBlank() }
            .filter { it.shouldMatch == null }
            .associate { field ->
                val customValue = when (field.type) {
                    FormFieldType.PHONE,
                    FormFieldType.NUMBER,
                    -> IntValue(field.value?.toIntOrNull() ?: 0)
                    else -> StringValue(field.value as String)
                }

                field.name to CustomValue(customValue)
            }

        return User(
            email = userFieldValues["email"]!!,
            username = userFieldValues["username"]!!,
            firstName = userFieldValues["firstName"]!!,
            lastName = userFieldValues["lastName"]!!,
            password = userFieldValues["password"]!!,
            isOfflineRegister = false,
            userCustomFields = userCustomFields,
        )
    }
}
