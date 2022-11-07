package com.safetorun.backendresilience.builders

import com.safetorun.backendresilience.dto.OSCheckConfigurationOffDevice
import com.safetorun.backendresilience.dto.SingleOSCheckConfigurationOffDevice
import com.safetorun.resilienceshared.dto.Severity

/**
 * Build an os check configuration
 */
class OSCheckConfigurationBuilderOffDevice internal constructor() {
    private val osCheckList = mutableListOf<SingleOSCheckConfigurationOffDevice>()

    /**
     * add a single check
     *
     * @param bloc os check
     */
    fun singleCheck(severity: Severity, bloc: (SingleOSCheckBuilderOffDevice.() -> Unit)) {
        osCheckList.add(
            SingleOSCheckBuilderOffDevice(severity)
                .apply(bloc)
                .build()
        )
    }

    internal fun build(): OSCheckConfigurationOffDevice {
        return OSCheckConfigurationOffDevice(osCheckList)
    }
}
