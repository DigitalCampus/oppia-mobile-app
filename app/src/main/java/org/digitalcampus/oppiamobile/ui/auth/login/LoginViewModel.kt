package org.digitalcampus.oppiamobile.ui.auth.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.digitalcampus.oppiamobile.domain.model.User
import org.digitalcampus.oppiamobile.domain.useCases.TestApiClientUseCase
import org.digitalcampus.oppiamobile.domain.useCases.UserLoginUseCase
import org.digitalcampus.oppiamobile.ui.common.AppViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userLoginUseCase: UserLoginUseCase,
    private val testApiClientUseCase: TestApiClientUseCase,
) : AppViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    data class UiState(
        val loading: Boolean = false,
        val error: String? = null,
        val loginSuccess: Boolean = false,
        val user: User? = null,
    )

    init {
        // TODO for testing api client. Remove at end
        viewModelScope.launch {
            testApiClientUseCase()
        }
    }

    fun onErrorDialogClosed() {
        _uiState.update { it.copy(error = null) }
    }

    fun onLoginClick(username: String, password: String) {
        val result = validate(username, password)
        if (result){
            doLogin(username, password)
        }
    }

    private fun validate(username: String, password: String) : Boolean {
        var result = true
        if (username.isBlank()) {
            _uiState.update { it.copy(error = "Username is empty") }
            result = false
        } else if (password.isBlank()) {
            _uiState.update { it.copy(error = "Password is empty") }
            result = false
        }

        return result
    }

    private fun doLogin(username: String, password: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }

            try {
                val user = userLoginUseCase(username, password)

                Log.d(TAG, "doLogin: User: $user")

                _uiState.update { it.copy(error = "Login success", user = user) } // for testing


                // TODO Go to main activity
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }

                // TODO pending better error handle
            } finally {
                _uiState.update { it.copy(loading = false) }
            }
        }
    }
}
