package io.github.dllewellyn.safetorun.api

import io.github.dllewellyn.safetorun.models.models.DataWrappedSignatureResult
import io.github.dllewellyn.safetorun.models.models.DataWrappedVerifyResult
import io.github.dllewellyn.safetorun.models.models.DeviceInformationDto
import io.github.dllewellyn.safetorun.models.models.DeviceSignatureDto

class DefaultSafeToRunApi(private val httpClient: SafeToRunHttpClient, apiKey: String) : SafeToRunApi {

    private val headers by lazy { mapOf(API_KEY_HEADER_NAME to apiKey) }

    override fun postNewDevice(deviceInformation: DeviceInformationDto): DataWrappedSignatureResult {
        return httpClient.post(
            DEVICE_CHECK_ENDPOINT,
            headers,
            deviceInformation,
            DeviceInformationDto.serializer(),
            DataWrappedSignatureResult.serializer()
        )
    }

    override fun verifyDataResult(deviceSignature: DeviceSignatureDto): DataWrappedVerifyResult {
        return httpClient.post(
            VERIFY_CHECK_ENDPOINT,
            headers,
            deviceSignature,
            DeviceSignatureDto.serializer(),
            DataWrappedVerifyResult.serializer()
        )
    }

    companion object {
        internal const val DEVICE_CHECK_ENDPOINT = "/deviceCheck"
        internal const val VERIFY_CHECK_ENDPOINT = "/verifyCheck"
        internal const val API_KEY_HEADER_NAME = "x-api-key"
    }
}
