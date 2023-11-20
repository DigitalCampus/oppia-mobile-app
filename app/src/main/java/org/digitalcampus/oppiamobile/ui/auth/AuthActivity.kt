package org.digitalcampus.oppiamobile.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.digitalcampus.oppiamobile.R
import org.digitalcampus.oppiamobile.databinding.ActivityWelcomeBinding
import org.digitalcampus.oppiamobile.domain.model.User

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    fun onSuccessUserAccess(user: User, firstLogin: Boolean) {
        findNavController(R.id.nav_host_fragment).navigate(R.id.mainActivity)
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


//    private fun setMenuOverflowIconColor(tab: Int) {
//        // Change color of 3 dots icon to ensure contrast
//        val toolbar: Toolbar = findViewById(R.id.toolbar)
//        val colorInt = ContextCompat.getColor(this, menuOverflowIconColor[tab]!!)
//        toolbar.overflowIcon!!.colorFilter = PorterDuffColorFilter(colorInt, PorterDuff.Mode.SRC_IN)
//    }


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
