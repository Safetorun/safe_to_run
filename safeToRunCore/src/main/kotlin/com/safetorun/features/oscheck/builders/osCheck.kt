package com.safetorun.features.oscheck.builders

import com.safetorun.checks.SafeToRunCheck
import com.safetorun.conditional.Conditional
import com.safetorun.features.oscheck.OSDetectionCheck
import com.safetorun.features.oscheck.OSDetectionConfig
import com.safetorun.features.oscheck.OSDetectionStrings

/**
 * Configure an os detection check
 *
 * @param strings the OS detection strings to use
 * @param conditional a list of conditionals
 */
fun osDetectionCheckConfig(strings: OSDetectionStrings, conditional: List<Conditional>): SafeToRunCheck {
    return OSDetectionCheck(OSDetectionConfig(conditional), strings)
}
