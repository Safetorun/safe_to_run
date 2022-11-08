package com.safetorun.backendresilience.builders

import com.safetorun.backendresilience.dto.SingleOSCheckConfigurationOffDevice
import com.safetorun.resilienceshared.builders.BaseSingleOSCheckBuilder
import com.safetorun.resilienceshared.builders.SingleOSCheckBuilder
import com.safetorun.resilienceshared.dto.Severity

/**
 * Build a single os check
 */
class SingleOSCheckBuilderOffDevice internal constructor(
    val severity: Severity,
    private val baseSingleOSCheckBuilder: BaseSingleOSCheckBuilder = BaseSingleOSCheckBuilder()
) :
    SingleOSCheckBuilder by baseSingleOSCheckBuilder {

    internal fun build() =
        baseSingleOSCheckBuilder.build()
            .run {
                SingleOSCheckConfigurationOffDevice(
                    allIntChecks = allIntChecks,
                    severity = severity,
                    allStringChecks = allStringChecks,
                    unlessIntChecks = unlessIntChecks,
                    unlessStringChecks = unlessStringChecks,
                )
            }

}
