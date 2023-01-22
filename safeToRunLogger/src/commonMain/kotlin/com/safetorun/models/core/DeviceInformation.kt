package com.safetorun.models.core

/**
 * The object used to send data to the server which can be used to
 * perform a device check
 */
@kotlinx.serialization.Serializable
internal data class DeviceInformation(
    val osCheck: OsCheck,
    val installOrigin: InstallOrigin,
    val blacklistedApp: BlacklistedApps,
    val signatureVerification: DeviceSignature,
) {
    companion object {
        fun empty() =
            DeviceInformation(
                OsCheck("", "", "", "", "", emptyList(), "", "", ""),
                InstallOrigin(""),
                BlacklistedApps(emptyList()),
                DeviceSignature("")
            )

    }
}
