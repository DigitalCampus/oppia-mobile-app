package org.digitalcampus.oppiamobile.utils

import android.graphics.Color
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import com.google.android.material.textfield.TextInputLayout
import org.digitalcampus.oppiamobile.BuildConfig

fun String.withAppIdPrefix() = "${BuildConfig.APPLICATION_ID}.$this"

fun TextInputLayout.markRequiredInRed() {
    hint = buildSpannedString {
        append(hint)
        color(Color.RED) { append(" *") }
    }
}
