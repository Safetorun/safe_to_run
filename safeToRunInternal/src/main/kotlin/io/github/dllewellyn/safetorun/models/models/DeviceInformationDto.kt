package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable

@Serializable
class DeviceInformationDto {
    var apiKey: String = ""
    var deviceId: String = ""
    var osCheck: OsCheckDto = OsCheckDto()
    var installOrigin: InstallOriginDto = InstallOriginDto()
    var blacklistedApp: BlacklistedAppsDto = BlacklistedAppsDto()
    var signatureVerification: SignatureVerification = SignatureVerification()
}
