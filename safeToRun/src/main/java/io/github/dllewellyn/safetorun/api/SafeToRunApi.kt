package io.github.dllewellyn.safetorun.api

import io.github.dllewellyn.safetorun.models.models.DataWrappedSignatureResult
import io.github.dllewellyn.safetorun.models.models.DataWrappedVerifyResult
import io.github.dllewellyn.safetorun.models.models.DeviceInformationDto
import io.github.dllewellyn.safetorun.models.models.DeviceSignatureDto

interface SafeToRunApi {
    fun postNewDevice(deviceInformation: DeviceInformationDto): DataWrappedSignatureResult
    fun verifyDataResult(deviceSignature: DeviceSignatureDto): DataWrappedVerifyResult
}
