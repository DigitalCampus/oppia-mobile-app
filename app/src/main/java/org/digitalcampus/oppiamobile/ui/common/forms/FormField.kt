package org.digitalcampus.oppiamobile.ui.common.forms

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import org.digitalcampus.oppiamobile.R
import org.digitalcampus.oppiamobile.domain.model.FormField

abstract class FormField(
    context: Context,
    field: FormField? = null,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        if (field != null) {
            id = field.name.hashCode()
        }

        val layoutParams = MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        if (!field?.helperText.isNullOrBlank()) {
            layoutParams.bottomMargin = resources.getDimensionPixelSize(R.dimen.margin_large)
        }
        setLayoutParams(layoutParams)
    }

    abstract fun setError(error: String)
}
