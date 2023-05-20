package com.safetorun.offdevice

import android.content.Context
import com.safetorun.api.DefaultHttpClient
import com.safetorun.api.DefaultSafeToRunApi
import com.safetorun.api.SafeToRunApi
import com.safetorun.features.blacklistedapps.AndroidInstalledPackagesQuery
import com.safetorun.features.installorigin.getInstaller
import com.safetorun.features.oscheck.OSInformationQueryAndroid
import com.safetorun.features.rootdetection.rootDetectionCheck
import com.safetorun.logger.models.BlacklistedApps
import com.safetorun.logger.models.DeviceInformation
import com.safetorun.logger.models.DeviceSignature
import com.safetorun.logger.models.InstallOrigin
import com.safetorun.logger.models.OsCheck
import com.safetorun.logger.models.SafeToRunEvents
import com.safetorun.models.builders.deviceInformationBuilder
import com.safetorun.models.models.DeviceInformationDto
import com.safetorun.models.models.OsCheckDto
import com.safetorun.offdevice.SafeToRunOffDeviceCache.safeToRunOffDeviceLazy
import com.safetorun.offdevice.builders.BlacklistedAppsOffDeviceBuilder
import com.safetorun.offdevice.builders.CompositeBuilder
import com.safetorun.offdevice.builders.InstallOriginOffDeviceBuilder
import com.safetorun.offdevice.builders.OSCheckOffDeviceBuilder
import com.safetorun.offdevice.builders.RootCheckOffDeviceBuilder
import com.safetorun.repository.AndroidDeviceIdRepository
import java.util.concurrent.Executors

internal class AndroidSafeToRunOffDevice internal constructor(
    private val safeToRunApi: SafeToRunApi,
    private val offDeviceResultBuilder: OffDeviceResultBuilder,
    private val apiKey: String,
    private val deviceId: String
) :
    SafeToRunOffDevice {
    private val executor by lazy { Executors.newSingleThreadExecutor() }

    override fun isSafeToRun(callback: (SafeToRunOffDeviceResult) -> Unit) {
        executor.execute { callback(isSafeToRun()) }
    }

    override fun isSafeToRun(): SafeToRunOffDeviceResult =
        safeToRunApi.postNewDevice(
            deviceInformationBuilder(apiKey) {
                deviceId(deviceId)
                offDeviceResultBuilder.buildOffDeviceResultBuilder(this)
            }
        ).run {
            SafeToRunOffDeviceResult(signature)
        }
}

private object SafeToRunOffDeviceCache {
    val safeToRunOffDeviceLazy: MutableMap<String, SafeToRunOffDevice> = mutableMapOf()
}

/**
 * Build a safe to run off device check
 *
 * @param url the url of the backend to send the check to
 * @param apiKey the api key of the backend service
 *
 * @receiver the app context
 *
 * @return an instance of [SafeToRunOffDevice]
 */
fun Context.safeToRunOffDevice(
    url: String,
    apiKey: String
) = safeToRunOffDevice(
    url,
    apiKey,
    offDeviceResultBuilder(),
    AndroidDeviceIdRepository(this).getOrCreateDeviceIdSync()
)

typealias SafeToRunLogger = (SafeToRunEvents) -> Unit

/**
 * Build a safe to run logger
 */
fun Context.safeToRunLogger(
    apiKey: String,
    url: String = "https://api.safetorun.com"
): SafeToRunLogger {
    if (safeToRunOffDeviceLazy.containsKey(apiKey)) {
        requireNotNull(safeToRunOffDeviceLazy[apiKey])
    }

    val api = DefaultSafeToRunApi(
        DefaultHttpClient(url),
        apiKey,
    )


    return {
        val deviceInformation = offDeviceResultBuilder()
            .buildOffDeviceResultBuilder(deviceInformationBuilder(apiKey))
            .build()

        val event = when (it) {
            is SafeToRunEvents.FailedCheck -> it.copy(deviceInformation = deviceInformation.toDeviceInformation())
            is SafeToRunEvents.SucceedCheck -> it.copy(deviceInformation = deviceInformation.toDeviceInformation())
        }
        api.logEvent(event)
    }
}

internal fun safeToRunOffDevice(
    url: String,
    apiKey: String,
    offDeviceResultBuilder: OffDeviceResultBuilder,
    deviceId: String
): SafeToRunOffDevice {
    if (safeToRunOffDeviceLazy.containsKey(apiKey)) {
        return requireNotNull(safeToRunOffDeviceLazy[apiKey])
    }

    return AndroidSafeToRunOffDevice(
        DefaultSafeToRunApi(
            DefaultHttpClient(url),
            apiKey,
        ),
        offDeviceResultBuilder,
        apiKey,
        deviceId
    ).also { safeToRunOffDeviceLazy[apiKey] = it }
}

/**
 * Build a safe to run off device result
 *
 * @receiver the app context
 *
 * @return an instance of [OffDeviceResultBuilder]
 */
fun Context.offDeviceResultBuilder(): OffDeviceResultBuilder = CompositeBuilder(
    listOf(
        OSCheckOffDeviceBuilder(OSInformationQueryAndroid()),
        InstallOriginOffDeviceBuilder { getInstaller() },
        BlacklistedAppsOffDeviceBuilder(AndroidInstalledPackagesQuery(this)),
        RootCheckOffDeviceBuilder { this.rootDetectionCheck() }
    )
)


private fun OsCheckDto.toOsCheck() = OsCheck(
    osVersion = this.osVersion,
    manufacturer = this.manufacturer,
    model = this.model,
    board = this.board,
    bootloader = this.bootloader,
    cpuAbi = this.cpuAbi,
    host = this.host,
    hardware = this.hardware,
    device = this.device
)

private fun DeviceInformationDto.toDeviceInformation() = DeviceInformation(
    osCheck = osCheck.toOsCheck(),
    installOrigin = InstallOrigin(installOrigin.installOriginPackageName ?: ""),
    blacklistedApp = BlacklistedApps(blacklistedApp.installedPackages),
    signatureVerification = DeviceSignature(
        signatureVerification.signatureVerificationString ?: ""
    ),
    isRooted = isRooted

)
