package io.github.dllewellyn.safetorun.backend.features.installorigin

import io.github.dllewellyn.safetorun.models.models.DeviceInformationDto
import io.github.dllewellyn.safetorun.models.models.InstallOriginDto
import io.github.dllewellyn.safetorun.checks.SafeToRunCheck
import io.github.dllewellyn.safetorun.features.installorigin.AmazonStore
import io.github.dllewellyn.safetorun.features.installorigin.GooglePlayStore
import io.github.dllewellyn.safetorun.features.installorigin.InstallOrigin
import io.github.dllewellyn.safetorun.features.installorigin.InstallOriginQuery
import io.github.dllewellyn.safetorun.features.installorigin.installOrigin
import io.micronaut.context.BeanContext

internal class OffDeviceInstallOriginCheck(private val installOriginDto: InstallOriginDto) : InstallOriginQuery {
    override fun getInstallPackageName(): String? {
        return installOriginDto.installOriginPackageName
    }
}

/**
 * Retrieve a safe to run check which will verify that the app was installed
 * by either google or amazon (plus any parameters passed in)
 *
 * @param allowedOrigins a list of places that it is permissible for your app to
 * have been installed from
 */
internal fun DeviceInformationDto.installOriginCheckWithDefaults(
    context: BeanContext,
    vararg allowedOrigins: String
): SafeToRunCheck {
    return installOriginCheckWithoutDefaults(
        context,
        GooglePlayStore().originPackage,
        AmazonStore().originPackage,
        *allowedOrigins
    )
}

/**
 * Retrieve a safe to run check which will verify that the app was installed
 * by either google or amazon (plus any parameters passed in)
 *
 * @param allowedOrigins a list of places that it is permissible for your app to
 * have been installed from
 */
fun DeviceInformationDto.installOriginCheckWithoutDefaults(
    context: BeanContext,
    vararg allowedOrigins: String
): SafeToRunCheck {
    return installOrigin(
        OffDeviceInstallOriginCheck(installOrigin),
        context.getBean(OffDeviceInstallOriginStrings::class.java)
    ) {
        allowedOrigins
            .map(::InstallOrigin)
            .forEach(::plus)
    }
}
