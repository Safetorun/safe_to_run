package io.github.dllewellyn.safetorun.backend.features.blacklistedapps

import io.github.dllewellyn.safetorun.models.models.BlacklistedAppsDto
import io.github.dllewellyn.safetorun.models.models.DeviceInformationDto
import io.github.dllewellyn.safetorun.features.blacklistedapps.BlacklistedAppCheck
import io.github.dllewellyn.safetorun.features.blacklistedapps.BlacklistedAppConfiguration
import io.github.dllewellyn.safetorun.features.blacklistedapps.BlacklistedAppStrings
import io.github.dllewellyn.safetorun.features.blacklistedapps.blacklistConfig
import io.micronaut.context.BeanContext

internal class OffDeviceBlacklistedAppCheck(private val blacklistedAppsDto: BlacklistedAppsDto) :
    BlacklistedAppCheck {

    override fun isAppPresent(packageName: String): Boolean {
        return blacklistedAppsDto.installedPackages.contains(packageName)
    }
}

/**
 * Add a check for a blacklisted app.
 *
 * Usage is like this:
 * `blacklistedAppCheck(context) {
 *      +"com.illegal.error"
 * }.error()
 * `
 */
fun DeviceInformationDto.blacklistedAppCheck(
    context: BeanContext,
    block: BlacklistedAppConfiguration.() -> Unit
) =
    blacklistConfig(
        OffDeviceBlacklistedAppCheck(blacklistedApp),
        context.getBean(BlacklistedAppStrings::class.java), block
    )