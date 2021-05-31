package io.github.dllewellyn.safetorun.features.oscheck

import io.github.dllewellyn.safetorun.checks.SafeToRunCheck
import io.github.dllewellyn.safetorun.conditional.Conditional

/**
 * Configure an os detection check
 *
 * @param strings the OS detection strings to use
 * @param conditional a list of conditionals
 */
fun osDetectionCheckConfig(strings: OSDetectionStrings, conditional: List<Conditional>): SafeToRunCheck {
    return OSDetectionCheck(OSDetectionConfig(conditional), strings)
}
