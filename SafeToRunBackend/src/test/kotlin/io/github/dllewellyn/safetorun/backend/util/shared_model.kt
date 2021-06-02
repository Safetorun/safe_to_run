package io.github.dllewellyn.safetorun.backend.util

import io.github.dllewellyn.safetorun.backend.util.Constants.ApiKey
import io.github.dllewellyn.safetorun.backend.util.Constants.DeviceId
import io.github.dllewellyn.safetorun.features.installorigin.GooglePlayStore
import io.github.dllewellyn.safetorun.models.builders.deviceInformationBuilder
import java.util.UUID

val easilyAcceptableModel
    get() = deviceInformationBuilder(ApiKey) {
        deviceId(DeviceId)
        installedApplication("com.a.b.c")
        installOrigin(GooglePlayStore().originPackage)

        osVersion("31")
        manufacturer("Google")

        model("model")
        board("board")
        bootloader("bootloader")
        cpuAbi("abi")
        cpuAbi("abi2")
        host("host")
        hardware("hardware")
        device("device")
        signature("")
    }

object Constants {
    const val ApiKey = "ApiKey"
    val DeviceId = UUID.randomUUID().toString()
}
