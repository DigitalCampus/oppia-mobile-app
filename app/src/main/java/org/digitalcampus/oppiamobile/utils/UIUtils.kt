/*
 * This file is part of OppiaMobile - https://digital-campus.org/
 *
 * OppiaMobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OppiaMobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OppiaMobile. If not, see <http://www.gnu.org/licenses/>.
 */
package org.digitalcampus.oppiamobile.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import org.digitalcampus.oppiamobile.R

object UIUtils {
    val TAG = UIUtils::class.simpleName
    private const val EXCEPTION = "Exception:"
    private var pointsToSubstractForAnimationSaved = 0

//    @JvmStatic
//    @JvmOverloads
//    fun showUserData(
//        menu: Menu?,
//        ctx: Context,
//        courseInContext: Course?,
//        animateBgPoints: Boolean = false,
//        pointsToSubstractForAnimation: Int = -1
//    ) {
//        if (menu == null) {
//            return
//        }
//
//        if (pointsToSubstractForAnimation > -1) {
//            pointsToSubstractForAnimationSaved = pointsToSubstractForAnimation
//        }
//
//        Log.i(
//            TAG,
//            "showUserData: --> pointsToSubstractForAnimationSaved: $pointsToSubstractForAnimationSaved"
//        )
//
//        val pointsItem = menu.findItem(R.id.points)
//        val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
//
//        //Get User from AppModule with dagger
//        val app = ctx.applicationContext as App
//        val u = app.component.user
//
//        Log.d(TAG, "Username: " + u.username + " | Points:" + u.points)
//
//        pointsItem?.let { pointsItem ->
//            val points: TextView? = pointsItem.actionView?.findViewById(R.id.userpoints)
//            points?.let { points ->
//                if (animateBgPoints) {
//                    val colorFrom = ContextCompat.getColor(ctx, R.color.white)
//                    val colorTo = ContextCompat.getColor(ctx, R.color.points_badge)
//                    val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
//                    colorAnimation.duration = 1000 // milliseconds
//                    colorAnimation.addUpdateListener { animator: ValueAnimator ->
//                        points.background.colorFilter =
//                            BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
//                                animator.animatedValue as Int,
//                                BlendModeCompat.SRC_OVER
//                            )
//                    }
//                    colorAnimation.start()
//                }
//                val scoringEnabled = prefs.getBoolean(PrefsActivity.PREF_SCORING_ENABLED, true)
//                if (scoringEnabled) {
//                    points.visibility = View.VISIBLE
//                    points.text = "${u.points - pointsToSubstractForAnimationSaved}"
//                    points.setOnClickListener {
//                        val i = Intent(ctx, ScorecardActivity::class.java)
//                        val tb = Bundle()
//                        tb.putString(ScorecardActivity.TAB_TARGET, ScorecardActivity.TAB_TARGET_POINTS)
//                        if (courseInContext != null) {
//                            tb.putSerializable(Course.TAG, courseInContext)
//                        }
//                        i.putExtras(tb)
//                        ctx.startActivity(i)
//                    }
//                } else {
//                    points.visibility = View.GONE
//                }
//            }
//        }
//    }


    fun showAlert(
        context: Context,
        message: String,
        title: String? = null,
        btnText: String = context.getString(R.string.close),
        cancelable: Boolean = true,
        onCancel: () -> Unit = {},
    ) {
        AlertDialog.Builder(context).apply {
            setTitle(title)
            setMessage(message)
            setCancelable(cancelable)
            setNegativeButton(btnText) { dialog: DialogInterface, _: Int -> dialog.cancel() }
            setOnCancelListener { onCancel() }
            show()
        }
    }


//    /**
//     * @param ctx
//     * @param langs
//     * @param prefs
//     * @param funct
//     */
//    @JvmStatic
//    fun createLanguageDialog(
//        ctx: Context,
//        langs: List<Lang>,
//        prefs: SharedPreferences,
//        funct: Callable<Boolean?>
//    ) {
//        val langStringList = ArrayList<String>()
//        val languagesList = langs.distinctBy { it.language } // make sure there aren't any duplicates
//
//        var prefLangPosition = -1
//        val prefLanguage = prefs.getString(PrefsActivity.PREF_CONTENT_LANGUAGE, Locale.getDefault().language)
//        languagesList.forEachIndexed { index, lang ->
//            val locale = Locale(lang.language)
//            val langDisp = locale.getDisplayLanguage(locale)
//            langStringList.add(langDisp)
//            if (lang.language.equals(prefLanguage, ignoreCase = true)) {
//                prefLangPosition = index
//            }
//        }
//
//        // only show if at least one language
//        if (languagesList.isNotEmpty()) {
//            val arr = ArrayAdapter(ctx, android.R.layout.select_dialog_singlechoice, langStringList)
//            val mAlertDialog = AlertDialog.Builder(ctx)
//                .setSingleChoiceItems(arr, prefLangPosition) { dialog, whichButton ->
//                    val newLang = languagesList[whichButton].language
//                    val editor = prefs.edit()
//                    editor.putString(PrefsActivity.PREF_CONTENT_LANGUAGE, newLang)
//                    editor.apply()
//                    dialog.dismiss()
//                    try {
//                        funct.call()
//                    } catch (e: Exception) {
//                        Analytics.logException(e)
//                        Log.d(TAG, EXCEPTION, e)
//                    }
//                }
//                .setTitle(ctx.getString(R.string.change_content_language))
//                .setNegativeButton(ctx.getString(R.string.cancel)) { dialog, _ -> dialog.dismiss() }
//                .create()
//            mAlertDialog.show()
//        }
//    }
//
//    @JvmStatic
//    fun hideSoftKeyboard(activity: Activity) {
//        hideSoftKeyboard(activity.window)
//    }
//
//    fun hideSoftKeyboard(dialog: Dialog) {
//        hideSoftKeyboard(dialog.window)
//    }
//
//    fun hideSoftKeyboard(window: Window?) {
//        val view = window!!.currentFocus
//        hideSoftKeyboard(view)
//    }
//
//    @JvmStatic
//    fun hideSoftKeyboard(view: View?) {
//        try {
//            val imm = view!!.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.hideSoftInputFromWindow(view.windowToken, 0)
//        } catch (ignored: Exception) {
//        }
//    }
//
//    private const val BULLET_SPAN_GAP_WIDTH = 20
//
//    @JvmStatic
//    fun getFromHtmlAndTrim(text: String?): CharSequence {
//        val html = SpannableStringBuilder(HtmlCompat.fromHtml(text!!, HtmlCompat.FROM_HTML_MODE_COMPACT))
//        val spans = html.getSpans(0, html.length, BulletSpan::class.java)
//        for (span in spans) {
//            val spanStart = html.getSpanStart(span)
//            val spanEnd = html.getSpanEnd(span)
//            html.removeSpan(span)
//            html.setSpan(object : BulletSpan(BULLET_SPAN_GAP_WIDTH) {
//                override fun getLeadingMargin(first: Boolean): Int {
//                    return BULLET_SPAN_GAP_WIDTH * 3
//                }
//
//                override fun drawLeadingMargin(
//                    canvas: Canvas,
//                    paint: Paint,
//                    x: Int,
//                    dir: Int,
//                    top: Int,
//                    baseline: Int,
//                    bottom: Int,
//                    text: CharSequence,
//                    start: Int,
//                    end: Int,
//                    first: Boolean,
//                    layout: Layout?
//                ) {
//                    if (first) {
//                        val yPosition = (top + bottom) / 1.9f
//                        val xPosition = (x + dir * BULLET_SPAN_GAP_WIDTH).toFloat()
//                        canvas.drawCircle(xPosition, yPosition, 8f, paint)
//                    }
//                }
//            }, spanStart, spanEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        }
//        var start = 0
//        var end = html.length
//        while (start < end && Character.isWhitespace(html[start])) {
//            start++
//        }
//        while (end > start && Character.isWhitespace(html[end - 1])) {
//            end--
//        }
//        return html.subSequence(start, end)
//    }
//
//    @JvmStatic
//    fun showChangeTextSizeDialog(context: Context, callback: Runnable?) {
//        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
//        val textSizeValues = context.resources.getStringArray(R.array.TextSizeValues)
//        val selectedFontSize = prefs.getString(PrefsActivity.PREF_TEXT_SIZE, "16")
//        var checkedPosition = 0
//        for (i in textSizeValues.indices) {
//            val textSizeValue = textSizeValues[i]
//            if (TextUtilsJava.equals(textSizeValue, selectedFontSize)) {
//                checkedPosition = i
//                break
//            }
//        }
//        AlertDialog.Builder(context)
//            .setSingleChoiceItems(
//                R.array.TextSize,
//                checkedPosition
//            ) { dialog: DialogInterface, whichButton: Int ->
//                val newTextSize = textSizeValues[whichButton]
//                prefs.edit().putString(PrefsActivity.PREF_TEXT_SIZE, newTextSize).commit()
//                try {
//                    callback?.run()
//                } catch (e: Exception) {
//                    Analytics.logException(e)
//                    Log.d(TAG, EXCEPTION, e)
//                }
//                dialog.dismiss()
//            }.setTitle(context.getString(R.string.menu_change_text_size))
//            .setNegativeButton(context.getString(R.string.cancel), null)
//            .show()
//    }

}