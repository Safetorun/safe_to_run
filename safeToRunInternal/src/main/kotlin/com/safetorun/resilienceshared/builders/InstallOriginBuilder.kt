package com.safetorun.resilienceshared.builders

import com.safetorun.backendresilience.dto.InstallOriginCheckOffDevice
import com.safetorun.resilienceshared.dto.InstallOriginCheck
import com.safetorun.resilienceshared.dto.Severity

/**
 * Adding install origin builder
 */
interface InstallOriginBuilder {
    /**
     * Add an allowed install origin
     */
    operator fun String.unaryPlus()

    /**
     * Allow install origin
     */
    fun String.allowInstallOrigin()
}

/**
 * Builder for install origin configuration
 */
class BaseInstallOriginBuilder internal constructor() : InstallOriginBuilder {
    private val allowedInstallOriginCheck = mutableListOf<String>()

    /**
     * Add an allowed install origin
     */
    override operator fun String.unaryPlus() {
        allowedInstallOriginCheck.add(this)
    }

    /**
     * Allow install origin
     */
    override fun String.allowInstallOrigin() {
        allowedInstallOriginCheck.add(this)
    }

    internal fun build(): InstallOriginCheck {
        return InstallOriginCheck(allowedInstallOriginCheck)
    }
}
