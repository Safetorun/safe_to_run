package com.safetorun.plus

import com.safetorun.logger.models.SafeToRunEvents
import com.safetorun.plus.models.DeviceSignatureDto
import com.safetorun.models.models.VerifierResult
import com.safetorun.plus.models.DeviceInformationDto

internal interface SafeToRunApi {
    fun postNewDevice(deviceInformation: DeviceInformationDto): DeviceSignatureDto
    fun verifyDataResult(deviceSignature: DeviceSignatureDto): VerifierResult
    fun logEvent(event : SafeToRunEvents)
}
