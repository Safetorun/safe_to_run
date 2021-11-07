package io.github.dllewellyn.safetorun.features.oscheck.builders

import io.github.dllewellyn.safetorun.checks.SafeToRunCheck
import io.github.dllewellyn.safetorun.conditional.Conditional
import io.github.dllewellyn.safetorun.features.oscheck.OSDetectionCheck
import io.github.dllewellyn.safetorun.features.oscheck.OSDetectionConfig
import io.github.dllewellyn.safetorun.features.oscheck.OSDetectionStrings

/**
 * Configure an os detection check
 *
 * @param strings the OS detection strings to use
 * @param conditional a list of conditionals
 */
fun osDetectionCheckConfig(strings: OSDetectionStrings, conditional: List<Conditional>): SafeToRunCheck {
    return OSDetectionCheck(OSDetectionConfig(conditional), strings)
}
