package org.digitalcampus.oppiamobile.ui.auth.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.digitalcampus.oppiamobile.R
import org.digitalcampus.oppiamobile.databinding.FragmentLoginBinding
import org.digitalcampus.oppia.fragments.AppFragment
import org.digitalcampus.oppiamobile.utils.UIUtils

@AndroidEntryPoint
class LoginFragment : AppFragment() {

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var binding: FragmentLoginBinding

    companion object {
        @JvmStatic
        fun newInstance(): LoginFragment = LoginFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginBtn.setOnClickListener { v -> onLoginClick() }


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(lifecycle).collect { state ->

                Log.d(TAG, "uiState collect: $state")
                if (state.loading) {
                    showProgressDialog(getString(R.string.loading))
                } else {
                    hideProgressDialog()
                }

                if (state.error != null) {
                    UIUtils.showAlert(
                        requireContext(),
                        state.error,
                        onCancel = { viewModel.onErrorDialogClosed() })
                }

                if (state.loginSuccess) {
                    toast("Login success")
                }

            }

        }
//        binding.btnResetPassword.setOnClickListener { v ->
//            val wa = activity as WelcomeActivity?
//            wa!!.switchTab(WelcomeActivity.TAB_RESET_PASSWORD)
//        }
//        binding.actionRegisterBtn.setOnClickListener { v ->
//            val wa = activity as WelcomeActivity?
//            wa!!.switchTab(WelcomeActivity.TAB_REGISTER)
//        }
//        binding.btnRememberUsername.setOnClickListener { v ->
//            val wa = activity as WelcomeActivity?
//            wa!!.switchTab(WelcomeActivity.TAB_REMEMBER_USERNAME)
//        }
    }

    override fun onPause() {
        super.onPause()
        hideProgressDialog()
    }

    protected fun onLoginClick() {
        val username: String = binding.loginUsernameField.text.toString()
        val password: String = binding.loginPasswordField.text.toString()
        viewModel.onLoginClick(username, password)
    }


//    override fun submitComplete(response: EntityResult<User>) {
//        hideProgressDialog()
//        if (response.isSuccess) {
//            val user = response.entity
//            (activity as WelcomeActivity?)!!.onSuccessUserAccess(user, true)
//        } else {
//            val ctx: Context? = super.getActivity()
//            if (ctx != null) {
//                UIUtils.showAlert(ctx, R.string.title_login, response.resultMessage)
//            }
//        }
//    }

}