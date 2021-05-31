package io.github.dllewellyn.safetorun.offdevice

import android.content.Context
import io.github.dllewellyn.safetorun.api.DefaultHttpClient
import io.github.dllewellyn.safetorun.api.DefaultSafeToRunApi
import io.github.dllewellyn.safetorun.api.SafeToRunApi
import io.github.dllewellyn.safetorun.features.installorigin.AndroidInstallOriginQuery
import io.github.dllewellyn.safetorun.features.oscheck.OSInformationQueryAndroid
import io.github.dllewellyn.safetorun.models.models.deviceInformationBuilder
import io.github.dllewellyn.safetorun.features.blacklistedapps.AndroidInstalledPackagesQuery
import io.github.dllewellyn.safetorun.offdevice.SafeToRunOffDeviceCache.safeToRunOffDeviceLazy
import io.github.dllewellyn.safetorun.offdevice.builders.BlacklistedAppsOffDeviceBuilder
import io.github.dllewellyn.safetorun.offdevice.builders.CompositeBuilder
import io.github.dllewellyn.safetorun.offdevice.builders.InstallOriginOffDeviceBuilder
import io.github.dllewellyn.safetorun.offdevice.builders.OSCheckOffDeviceBuilder
import io.github.dllewellyn.safetorun.repository.AndroidDeviceIdRepository
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
    CompositeBuilder(
        listOf(
            OSCheckOffDeviceBuilder(OSInformationQueryAndroid()),
            InstallOriginOffDeviceBuilder(AndroidInstallOriginQuery(this)),
            BlacklistedAppsOffDeviceBuilder(AndroidInstalledPackagesQuery(this))
        )
    ),
    AndroidDeviceIdRepository(this).getOrCreateDeviceIdSync()
)

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
