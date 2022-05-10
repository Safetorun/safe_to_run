package com.safetorun.api

import com.safetorun.models.models.DeviceInformationDto
import com.safetorun.models.models.DeviceSignatureDto
import com.safetorun.models.models.VerifierResult

internal interface SafeToRunApi {
    fun postNewDevice(deviceInformation: DeviceInformationDto): DeviceSignatureDto
    fun verifyDataResult(deviceSignature: DeviceSignatureDto): VerifierResult
}
