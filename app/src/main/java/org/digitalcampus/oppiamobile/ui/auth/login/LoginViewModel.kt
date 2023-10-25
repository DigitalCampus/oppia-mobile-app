package org.digitalcampus.oppiamobile.ui.auth.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.digitalcampus.oppiamobile.domain.useCases.UserLoginLocalUseCase
import org.digitalcampus.oppiamobile.domain.useCases.UserLoginRemoteUseCase
import org.digitalcampus.oppiamobile.ui.common.AppViewModel
import org.digitalcampus.oppiamobile.utils.ConnectivityUtils
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userLoginRemoteUseCase: UserLoginRemoteUseCase,
    private val userLoginLocalUseCase: UserLoginLocalUseCase,
    private val connectivityUtils: ConnectivityUtils,
) : AppViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    data class UiState(
        val loading: Boolean = false,
        val error: String? = null,
        val loginSuccess: Boolean = false,
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
            doLogin(username, password)
        }
    }

    private fun doLogin(username: String, password: String) {
        val isConnected = connectivityUtils.isConnected()

        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }

            try {
                val user =
                    if (isConnected) {
                        userLoginRemoteUseCase(username, password)
                    } else {
                        userLoginLocalUseCase(username, password)
                    }

                Log.d(TAG, "doLogin: User: $user")

                _uiState.update { it.copy(error = "Login success") } // for testing

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
