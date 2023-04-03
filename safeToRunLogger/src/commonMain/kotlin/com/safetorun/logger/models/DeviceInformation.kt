package com.safetorun.logger.models

/**
 * The object used to send data to the server which can be used to
 * perform a device check
 */
@kotlinx.serialization.Serializable
 data class DeviceInformation(
    val osCheck: OsCheck,
    val installOrigin: InstallOrigin,
    val blacklistedApp: BlacklistedApps,
    val signatureVerification: DeviceSignature,
    val isRooted : Boolean
) {
    companion object {
        fun empty() =
            DeviceInformation(
                OsCheck("", "", "", "", "", emptyList(), "", "", ""),
                InstallOrigin(""),
                BlacklistedApps(emptyList()),
                DeviceSignature(""),
                false
            )

    }
}
