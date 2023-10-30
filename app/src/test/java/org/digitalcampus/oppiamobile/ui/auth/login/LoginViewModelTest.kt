package org.digitalcampus.oppiamobile.ui.auth.login

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.digitalcampus.oppiamobile.domain.model.User
import org.digitalcampus.oppiamobile.domain.useCases.TestApiClientUseCase
import org.digitalcampus.oppiamobile.domain.useCases.UserLoginUseCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import utils.MainCoroutineRule

internal class LoginViewModelTest {

    private val userLoginUseCase = mock<UserLoginUseCase>()
    private val testApiClientUseCase = mock<TestApiClientUseCase>()

    val viewmodel by lazy { LoginViewModel(userLoginUseCase, testApiClientUseCase) }

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Test
    fun `test login successful`() = runTest {
        val expectedUser = User(
            email = "testEmail@test.com",
            username = "testUsername",
            firstName = "Test",
            lastName = "User",
            apiKey = "testApiKey"
        )
        whenever(userLoginUseCase.invoke(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
            .thenReturn(expectedUser)

        viewmodel.onLoginClick("testUsername", "Some Password")
        advanceUntilIdle()
        Assert.assertEquals(expectedUser, viewmodel.uiState.value.user)
    }

    @Test
    fun `test user is empty`() = runTest {
        viewmodel.onLoginClick("", "")
        Assert.assertEquals("Username is empty", viewmodel.uiState.value.error)
    }

    @Test
    fun `test password is empty`() = runTest {
        viewmodel.onLoginClick("someUsername", "")
        Assert.assertEquals("Password is empty", viewmodel.uiState.value.error)
    }
}