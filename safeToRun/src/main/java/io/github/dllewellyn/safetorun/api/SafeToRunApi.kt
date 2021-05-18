package io.github.dllewellyn.safetorun.api

import io.github.dllewellyn.safetorun.models.models.DeviceInformationDto
import io.github.dllewellyn.safetorun.models.models.DeviceSignatureDto
import io.github.dllewellyn.safetorun.models.models.VerifierResult

internal interface SafeToRunApi {
    fun postNewDevice(deviceInformation: DeviceInformationDto): DeviceSignatureDto
    fun verifyDataResult(deviceSignature: DeviceSignatureDto): VerifierResult
}
