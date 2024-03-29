package com.safetorun.models.core

/**
 * The object used to send data to the server which can be used to
 * perform a device check
 */
data class DeviceInformation(
    val osCheck: OsCheck,
    val installOrigin: InstallOrigin,
    val blacklistedApp: BlacklistedApps,
    val signatureVerification: DeviceSignature,
    val isRooted : Boolean
)
