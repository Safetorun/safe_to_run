package com.safetorun.exploration



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

///**
// * Convert device information DTO to a Core object
// */
//fun DeviceInformationDto.toDeviceInformation() =
//    DeviceInformation(
//        osCheck = OsCheck(
//            osVersion = osCheck.osVersion,
//            manufacturer = osCheck.manufacturer,
//            model = osCheck.model,
//            board = osCheck.board,
//            bootloader = osCheck.bootloader,
//            cpuAbi = osCheck.cpuAbi,
//            host = osCheck.host,
//            hardware = osCheck.hardware,
//            device = osCheck.device
//        ),
//        installOrigin = InstallOrigin(installOrigin.installOriginPackageName ?: ""),
//        signatureVerification = SignatureInformation(signatureVerification.signatureVerificationString ?: "")
//    )
