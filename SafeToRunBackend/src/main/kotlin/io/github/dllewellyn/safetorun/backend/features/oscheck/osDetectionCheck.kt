package io.github.dllewellyn.safetorun.backend.features.oscheck

import io.github.dllewellyn.safetorun.checks.SafeToRunCheck
import io.github.dllewellyn.safetorun.conditional.Conditional
import io.github.dllewellyn.safetorun.features.oscheck.OSDetectionCheck
import io.github.dllewellyn.safetorun.features.oscheck.OSDetectionConfig
import io.github.dllewellyn.safetorun.features.oscheck.OSDetectionStrings
import io.micronaut.context.BeanContext

fun osDetectionCheck(context: BeanContext, vararg conditional: Conditional): SafeToRunCheck {
    return OSDetectionCheck(
        OSDetectionConfig(conditional.toList()),
        context.getBean(OSDetectionStrings::class.java)
    )
}
