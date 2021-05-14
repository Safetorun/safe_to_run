package io.github.dllewellyn.safetorun.backend.util

import io.github.dllewellyn.safetorun.models.models.BlacklistedAppsDto
import io.github.dllewellyn.safetorun.models.models.DeviceInformationDto
import io.github.dllewellyn.safetorun.models.models.InstallOriginDto
import io.github.dllewellyn.safetorun.models.models.OsCheckDto
import io.github.dllewellyn.safetorun.models.models.SignatureVerification
import io.github.dllewellyn.safetorun.backend.util.Constants.ApiKey
import io.github.dllewellyn.safetorun.backend.util.Constants.DeviceId
import io.github.dllewellyn.safetorun.features.installorigin.GooglePlayStore
import java.util.UUID

val easilyAcceptableModel
    get() = DeviceInformationDto().apply {
        apiKey = ApiKey
        deviceId = DeviceId
        blacklistedApp = BlacklistedAppsDto().apply {
            installedPackages = listOf("com.a.b.c")
        }
        installOrigin = InstallOriginDto().apply {
            installOriginPackageName = GooglePlayStore().originPackage
        }
        osCheck = OsCheckDto().apply {
            osVersion = "31"
            manufacturer = "Google"
        }
        signatureVerification = SignatureVerification().apply {
            signatureVerificationString = ""
        }
    }

object Constants {
    const val ApiKey = "ApiKey"
    val DeviceId = UUID.randomUUID().toString()
}
