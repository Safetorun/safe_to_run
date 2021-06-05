package io.github.dllewellyn.safetorun.exploration

import io.github.dllewellyn.safetorun.models.models.DeviceInformationDto

/**
 * @param osCheck results of the os check
 * @param installOrigin install origin information
 * @param signatureVerification signature verification information
 */
data class DeviceInformation(
    val osCheck: OsCheck,
    val installOrigin: InstallOrigin,
    val signatureVerification: SignatureInformation
)

fun DeviceInformationDto.toDeviceInformation() =
    DeviceInformation(
        osCheck = OsCheck(
            osVersion = osCheck.osVersion,
            manufacturer = osCheck.manufacturer,
            model = osCheck.model,
            board = osCheck.board,
            bootloader = osCheck.bootloader,
            cpuAbi = osCheck.cpuAbi,
            host = osCheck.host,
            hardware = osCheck.hardware,
            device = osCheck.device
        ),
        installOrigin = InstallOrigin(installOrigin.installOriginPackageName ?: ""),
        signatureVerification = SignatureInformation(signatureVerification.signatureVerificationString ?: "")
    )
