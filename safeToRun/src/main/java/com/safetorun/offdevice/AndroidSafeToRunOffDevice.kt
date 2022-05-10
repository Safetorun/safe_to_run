package com.safetorun.offdevice

import android.content.Context
import com.safetorun.api.DefaultHttpClient
import com.safetorun.api.DefaultSafeToRunApi
import com.safetorun.api.SafeToRunApi
import com.safetorun.exploration.DeviceInformation
import com.safetorun.exploration.toDeviceInformation
import com.safetorun.features.blacklistedapps.AndroidInstalledPackagesQuery
import com.safetorun.features.installorigin.getInstaller
import com.safetorun.features.oscheck.OSInformationQueryAndroid
import com.safetorun.models.builders.deviceInformationBuilder
import com.safetorun.offdevice.SafeToRunOffDeviceCache.safeToRunOffDeviceLazy
import com.safetorun.offdevice.builders.BlacklistedAppsOffDeviceBuilder
import com.safetorun.offdevice.builders.CompositeBuilder
import com.safetorun.offdevice.builders.InstallOriginOffDeviceBuilder
import com.safetorun.offdevice.builders.OSCheckOffDeviceBuilder
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

/**
 * Get a device informatio DTO with information about the currently
 * running device. This can be useful to help deciding on where to set
 * your OS rules
 */
fun Context.deviceInformation(): DeviceInformation = offDeviceResultBuilder()
    .buildOffDeviceResultBuilder(deviceInformationBuilder(""))
    .build()
    .toDeviceInformation()

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

internal fun Context.offDeviceResultBuilder(): OffDeviceResultBuilder = CompositeBuilder(
    listOf(
        OSCheckOffDeviceBuilder(OSInformationQueryAndroid()),
        InstallOriginOffDeviceBuilder { getInstaller() },
        BlacklistedAppsOffDeviceBuilder(AndroidInstalledPackagesQuery(this))
    )
)
