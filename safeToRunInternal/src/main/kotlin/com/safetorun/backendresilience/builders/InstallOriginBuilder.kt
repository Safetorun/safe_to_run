package com.safetorun.backendresilience.builders

import com.safetorun.backendresilience.dto.InstallOriginCheckOffDevice
import com.safetorun.resilienceshared.dto.Severity

/**
 * Builder for install origin configuration
 */
class InstallOriginBuilder internal constructor(private val severity: Severity) {
    private val allowedInstallOriginCheck = mutableListOf<String>()

    /**
     * Add an allowed install origin
     */
    operator fun String.unaryPlus() {
        allowedInstallOriginCheck.add(this)
    }

    /**
     * Allow install origin
     */
    fun String.allowInstallOrigin() {
        allowedInstallOriginCheck.add(this)
    }

    internal fun build(): InstallOriginCheckOffDevice {
        return InstallOriginCheckOffDevice(allowedInstallOriginCheck, severity)
    }
}
