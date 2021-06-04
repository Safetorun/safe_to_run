package io.github.dllewellyn.safetorun.backend.features.oscheck

import io.github.dllewellyn.safetorun.checks.SafeToRunCheck
import io.github.dllewellyn.safetorun.conditional.Conditional
import io.github.dllewellyn.safetorun.features.oscheck.OSDetectionStrings
import io.github.dllewellyn.safetorun.features.oscheck.builders.osDetectionCheckConfig
import io.micronaut.context.BeanContext

/**
 * Add an os detection check based on a conditional. E.g
 *
 * `
 *  val conditional =  conditionalBuilder {
 *       with(minOsVersion(MIN_OS_VERSION))
 *       and(notManufacturer("Abc"))
 *       }
 *   osDetectionCheck(context, conditional).error()
 * `
 */
fun osDetectionCheck(context: BeanContext, vararg conditional: Conditional): SafeToRunCheck {
    return osDetectionCheckConfig(
        context.getBean(OSDetectionStrings::class.java),
        conditional.toList(),
    )
}
