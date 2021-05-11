package io.github.dllewellyn.safetorun.backend.models

import io.micronaut.core.annotation.Introspected

@Introspected
class DeviceInformationDto {
    var apiKey: String = ""
    var deviceId: String = ""
    var osCheck: OsCheckDto = OsCheckDto()
    var installOrigin: InstallOriginDto = InstallOriginDto()
    var blacklistedApp: BlacklistedAppsDto = BlacklistedAppsDto()
    var signatureVerification: SignatureVerification = SignatureVerification()
}