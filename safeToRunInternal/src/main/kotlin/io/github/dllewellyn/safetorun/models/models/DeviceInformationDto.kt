package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable

@Serializable
/**
 * The DTO used to send data to the server which can be used to
 * perform a device check
 */
class DeviceInformationDto internal constructor() {
    var apiKey: String = ""
    var deviceId: String = ""
    var osCheck: OsCheckDto = OsCheckDto()
    var installOrigin: InstallOriginDto = InstallOriginDto()
    var blacklistedApp: BlacklistedAppsDto = BlacklistedAppsDto()
    var signatureVerification: SignatureVerification = SignatureVerification()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DeviceInformationDto

        if (apiKey != other.apiKey) return false
        if (deviceId != other.deviceId) return false
        if (osCheck != other.osCheck) return false
        if (installOrigin != other.installOrigin) return false
        if (blacklistedApp != other.blacklistedApp) return false
        if (signatureVerification != other.signatureVerification) return false

        return true
    }

    override fun hashCode(): Int {
        var result = apiKey.hashCode()
        result = 31 * result + deviceId.hashCode()
        result = 31 * result + osCheck.hashCode()
        result = 31 * result + installOrigin.hashCode()
        result = 31 * result + blacklistedApp.hashCode()
        result = 31 * result + signatureVerification.hashCode()
        return result
    }

    override fun toString(): String {
        return "DeviceInformationDto(" +
                "apiKey='$apiKey', " +
                "deviceId='$deviceId', " +
                "osCheck=$osCheck, " +
                "installOrigin=$installOrigin, " +
                "blacklistedApp=$blacklistedApp, " +
                "signatureVerification=$signatureVerification)"
    }
}
