package org.digitalcampus.oppiamobile.ui.auth.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.digitalcampus.oppiamobile.domain.use_cases.CheckFieldNotEmpty
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {


    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    data class UiState(
        val loading: Boolean = false,
        val error: String? = null,
        val loginSuccess: Boolean = false
    )

    fun onErrorDialogClosed() {
        _uiState.update { it.copy(error = null) }
    }

    fun onLoginClick(username: String, password: String) {
        if (username.isBlank()) {
            _uiState.update { it.copy(error = "Username is emnpty") }
        } else if (password.isBlank()) {
            _uiState.update { it.copy(error = "Password is emnpty") }
        } else {
            _uiState.update { it.copy(loading = true) }
        }
    }
}