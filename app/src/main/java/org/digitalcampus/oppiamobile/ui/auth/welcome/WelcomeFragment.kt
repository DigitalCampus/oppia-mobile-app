package org.digitalcampus.oppiamobile.ui.auth.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.digitalcampus.oppia.fragments.AppFragment
import org.digitalcampus.oppiamobile.BuildConfig
import org.digitalcampus.oppiamobile.databinding.FragmentWelcomeBinding
import org.digitalcampus.oppiamobile.ui.auth.AuthActivity

class WelcomeFragment : AppFragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        binding.welcomeRegister.visibility = if (BuildConfig.ALLOW_REGISTER_USER) View.VISIBLE else View.GONE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.welcomeLogin.setOnClickListener {
            val authActivity: AuthActivity = activity as AuthActivity
            authActivity.switchTab(AuthActivity.TAB_LOGIN)
        }
        binding.welcomeRegister.setOnClickListener {
            val authActivity: AuthActivity = activity as AuthActivity
            authActivity.switchTab(AuthActivity.TAB_REGISTER)
        }
        // TODO: Add PrivacyActivity
//        binding.welcomePrivacyInfo.setOnClickListener {
//            val iA = Intent(activity, PrivacyActivity::class.java)
//            startActivity(iA)
//        }
    }

}