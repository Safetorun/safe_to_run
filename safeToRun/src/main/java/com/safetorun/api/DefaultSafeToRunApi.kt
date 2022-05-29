package com.safetorun.api

import com.safetorun.models.models.ConfirmVerificationRequestDto
import com.safetorun.models.models.DataWrappedSignatureResult
import com.safetorun.models.models.DataWrappedVerifyResult
import com.safetorun.models.models.DeviceInformationDto
import com.safetorun.models.models.DeviceSignatureDto
import com.safetorun.models.models.VerifierResult

internal class DefaultSafeToRunApi(private val httpClient: SafeToRunHttpClient, private val apiKey: String) :
    SafeToRunApi {

    private val headers by lazy { mapOf(API_KEY_HEADER_NAME to apiKey) }

    override fun postNewDevice(deviceInformation: DeviceInformationDto): DeviceSignatureDto {
        return httpClient.post(
            DEVICE_CHECK_ENDPOINT,
            headers,
            deviceInformation,
            DeviceInformationDto.serializer(),
            DataWrappedSignatureResult.serializer()
        ).data
    }

    override fun verifyDataResult(deviceSignature: DeviceSignatureDto): VerifierResult {
        return httpClient.post(
            VERIFY_CHECK_ENDPOINT,
            headers,
            ConfirmVerificationRequestDto().apply {
                signature = deviceSignature.signature
                apiKey = this@DefaultSafeToRunApi.apiKey
            },
            ConfirmVerificationRequestDto.serializer(),
            DataWrappedVerifyResult.serializer()
        ).data
    }

    companion object {
        internal const val DEVICE_CHECK_ENDPOINT = "/deviceCheck"
        internal const val VERIFY_CHECK_ENDPOINT = "/verifyCheck"
        internal const val API_KEY_HEADER_NAME = "x-api-key"
    }
}