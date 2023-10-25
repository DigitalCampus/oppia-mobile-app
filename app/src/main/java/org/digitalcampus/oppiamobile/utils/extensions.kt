package org.digitalcampus.oppiamobile.utils

import org.digitalcampus.oppiamobile.BuildConfig

fun String.withAppIdPrefix() = "${BuildConfig.APPLICATION_ID}.$this"