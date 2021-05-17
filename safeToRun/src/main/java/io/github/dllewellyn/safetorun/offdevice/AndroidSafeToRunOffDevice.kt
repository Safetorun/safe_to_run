package io.github.dllewellyn.safetorun.offdevice

import io.github.dllewellyn.safetorun.api.SafeToRunApi
import io.github.dllewellyn.safetorun.models.models.deviceInformation
import java.util.concurrent.Executors

class AndroidSafeToRunOffDevice(private val safeToRunApi: SafeToRunApi) : SafeToRunOffDevice {
    private val executor by lazy { Executors.newSingleThreadExecutor() }

    override fun isSafeToRun(callback: (SafeToRunOffDeviceResult) -> Unit) {
        executor.execute { callback(isSafeToRun()) }
    }

    override fun isSafeToRun(): SafeToRunOffDeviceResult =
        safeToRunApi.postNewDevice(
            deviceInformation("5bzdwZ8Drs1AIsmJAx0M37bndOeEkwbv6pI5fjx1") {
                osVersion("31")
                manufacturer("Manufacturer")
                installOrigin("Install origin")
                installedApplication("com.example")
                signature("Abcdef")
            }
        ).run {
            SafeToRunOffDeviceResult(data.signature)
        }
}
