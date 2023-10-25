package org.digitalcampus.oppiamobile.ui.common

import android.content.Context
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import org.digitalcampus.oppiamobile.R

class ActivityPagerAdapter(
    private val ctx: Context,
    fm: FragmentManager,
    private val fragments: List<Fragment>,
    private val tabTitles: List<String>,
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val TAG = ActivityPagerAdapter::class.simpleName

    override fun getItem(index: Int): Fragment {
        return fragments[index]
    }

    override fun getPageTitle(position: Int): CharSequence {
        val title = tabTitles[position]
//        val typefaceSpan = CalligraphyTypefaceSpan(TypefaceUtils.load(ctx.assets, "fonts/montserrat.ttf"))
        val s = SpannableStringBuilder()
//        s.append(title).setSpan(typefaceSpan, 0, title.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return SpannableString.valueOf(s)
    }

    override fun getCount(): Int {
        return fragments.size
    }

    private fun updateTabLayout(tab: TabLayout.Tab?, layoutId: Int, tabTitle: String?) {
        tab?.let {
            val v = LayoutInflater.from(ctx).inflate(layoutId, null)
            val tv = v.findViewById<TextView>(R.id.tabTitle)
            tv.text = tabTitle
            tab.customView = v
        }
    }

    fun updateTabViews(tabs: TabLayout) {
        if (tabs.tabCount == 1) {
            updateTabLayout(tabs.getTabAt(0), R.layout.tablayout_fullwidth_tab, tabTitles[0])
            return
        }
        for (i in 0 until tabs.tabCount) {
            updateTabLayout(tabs.getTabAt(i), R.layout.tablayout_fixed_tab, tabTitles[i])
        }
    }
}
