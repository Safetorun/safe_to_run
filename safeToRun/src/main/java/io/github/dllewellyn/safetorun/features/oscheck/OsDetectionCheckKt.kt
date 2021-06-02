package io.github.dllewellyn.safetorun.features.oscheck

import android.content.Context
import io.github.dllewellyn.safetorun.checks.SafeToRunCheck
import io.github.dllewellyn.safetorun.conditional.Conditional
import io.github.dllewellyn.safetorun.features.oscheck.builders.osDetectionCheckConfig

/**
 * Check the OS given the conditionals passed in
 *
 * @receiver application context
 *
 * @param conditional a list of conditionals
 *
 * @return a safe to run check that we can call `isSafeToRun()` on
 */
fun Context.osDetectionCheck(vararg conditional: Conditional): SafeToRunCheck {
    return osDetectionCheckConfig(AndroidOSDetectionStrings(this), conditional.toList())
}
