package com.safetorun.backendresilience.builders

import com.safetorun.backendresilience.dto.SingleOSCheckConfigurationOffDevice
import com.safetorun.resilienceshared.dto.Severity
import com.safetorun.resilienceshared.dto.SingleCheck

/**
 * Build a single os check
 */
class SingleOSCheckBuilder internal constructor(val severity: Severity) {
    var allChecks: List<SingleCheck> = emptyList()
    var osConfigurationName: String = ""

    internal fun build() = SingleOSCheckConfigurationOffDevice(allChecks = allChecks, severity = severity)
}
