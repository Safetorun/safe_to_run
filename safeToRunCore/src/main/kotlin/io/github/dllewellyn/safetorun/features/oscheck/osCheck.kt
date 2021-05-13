package io.github.dllewellyn.safetorun.features.oscheck

import io.github.dllewellyn.safetorun.checks.SafeToRunCheck
import io.github.dllewellyn.safetorun.conditional.Conditional

fun osDetectionCheckConfig(strings: OSDetectionStrings, conditional: List<Conditional>): SafeToRunCheck {
    return OSDetectionCheck(OSDetectionConfig(conditional), strings)
}
