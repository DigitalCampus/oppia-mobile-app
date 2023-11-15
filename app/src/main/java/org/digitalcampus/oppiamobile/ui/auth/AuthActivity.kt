package org.digitalcampus.oppiamobile.ui.auth

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import org.digitalcampus.oppiamobile.R
import org.digitalcampus.oppiamobile.databinding.ActivityWelcomeBinding
import org.digitalcampus.oppiamobile.ui.auth.login.LoginFragment
import org.digitalcampus.oppiamobile.ui.auth.welcome.WelcomeFragment
import org.digitalcampus.oppiamobile.ui.common.ActivityPagerAdapter

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    companion object {
        const val TAB_WELCOME = 0
        const val TAB_LOGIN = 1
        const val TAB_REGISTER = 2
        const val TAB_RESET_PASSWORD = 3
        const val TAB_REMEMBER_USERNAME = 4
    }

    private val menuOverflowIconColor = mapOf(
        TAB_WELCOME to R.color.text_dark,
        TAB_LOGIN to R.color.toolbar_icon_color,
        TAB_REGISTER to R.color.toolbar_icon_color,
        TAB_RESET_PASSWORD to R.color.text_dark,
        TAB_REMEMBER_USERNAME to R.color.text_dark,

    )

    private var currentTab = TAB_WELCOME
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        val fragments: MutableList<Fragment> = ArrayList()
        val tabTitles: MutableList<String> = ArrayList()

        fragments.add(WelcomeFragment())
        fragments.add(LoginFragment())
//        fragments.add(RegisterFragment())
//        fragments.add(ResetPasswordFragment())
//        fragments.add(RememberUsernameFragment())

        tabTitles.add(this.getString(R.string.tab_title_welcome))
        tabTitles.add(this.getString(R.string.tab_title_login))
//        tabTitles.add(this.getString(R.string.tab_title_register))
//        tabTitles.add(this.getString(R.string.tab_title_reset_password))
//        tabTitles.add(this.getString(R.string.tab_title_remember_username))

        val apAdapter = ActivityPagerAdapter(
            this,
            supportFragmentManager,
            fragments,
            tabTitles,
        )
        binding.activityWelcomePager.adapter = apAdapter
        binding.activityWelcomePager.currentItem = currentTab
        setMenuOverflowIconColor(currentTab)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.activity_welcome, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val itemId = item.itemId
//        return if (itemId == R.id.menu_settings) {
//            AdminSecurityManager.with(this).checkAdminPermission(itemId) {
//                val i = Intent(this, PrefsActivity::class.java)
//                startActivity(i)
//            }
//            true
//        } else if (itemId == R.id.menu_about) {
//            val iA = Intent(this, AboutActivity::class.java)
//            startActivity(iA)
//            true
//        } else if (itemId == R.id.menu_privacy) {
//            val iA = Intent(this, PrivacyActivity::class.java)
//            startActivity(iA)
//            true
//        } else {
//            super.onOptionsItemSelected(item)
//        }
//    }

    fun switchTab(tab: Int) {
        binding.activityWelcomePager.currentItem = tab
        currentTab = tab
        setMenuOverflowIconColor(tab)
    }

    private fun setMenuOverflowIconColor(tab: Int) {
        // Change color of 3 dots icon to ensure contrast
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val colorInt = ContextCompat.getColor(this, menuOverflowIconColor[tab]!!)
        toolbar.overflowIcon!!.colorFilter = PorterDuffColorFilter(colorInt, PorterDuff.Mode.SRC_IN)
    }

    override fun onBackPressed() {
        if (currentTab == TAB_WELCOME) {
            super.onBackPressed()
        } else {
            switchTab(TAB_WELCOME)
        }
    }

//    fun onSuccessUserAccess(user: User?, firstLogin: Boolean) {
//        val fromViewDigest = intent.getBooleanExtra(ViewDigestActivity.EXTRA_FROM_VIEW_DIGEST, false)
//        SessionManager.loginUser(this, user)
//        if (fromViewDigest) {
//            setResult(RESULT_OK)
//        } else {
//            val intent = Intent(this, MainActivity::class.java)
//            intent.putExtra(MainActivity.EXTRA_FIRST_LOGIN, firstLogin)
//            startActivity(intent)
//        }
//        if (analyticsProvider.shouldShowOptOutRationale(this)) {
//            overridePendingTransition(0, 0)
//            startActivity(Intent(this, AnalyticsOptinActivity::class.java))
//        }
//        FetchUserTask().updateLoggedUserProfile(this, apiEndpoint, user)
//        finish()
//    }
}
