package io.github.dllewellyn.safetorun.features.oscheck

import android.content.Context
import io.github.dllewellyn.safetorun.checks.SafeToRunCheck
import io.github.dllewellyn.safetorun.conditional.Conditional

fun Context.osDetectionCheck(vararg conditional: Conditional): SafeToRunCheck {
    return OSDetectionCheck(OSDetectionConfig(conditional.toList()), AndroidOSDetectionStrings(this))
}
