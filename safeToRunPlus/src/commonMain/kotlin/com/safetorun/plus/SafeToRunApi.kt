package com.safetorun.plus

import com.safetorun.logger.models.SafeToRunEvents
import com.safetorun.models.models.DeviceInformationDto
import com.safetorun.models.models.DeviceSignatureDto
import com.safetorun.models.models.VerifierResult

internal interface SafeToRunApi {
    fun postNewDevice(deviceInformation: DeviceInformationDto): DeviceSignatureDto
    fun verifyDataResult(deviceSignature: DeviceSignatureDto): VerifierResult
    fun logEvent(event : SafeToRunEvents)
}
