package com.safetorun.api

import com.google.common.truth.Truth.assertThat
import com.safetorun.api.DefaultSafeToRunApi.Companion.API_KEY_HEADER_NAME
import com.safetorun.api.DefaultSafeToRunApi.Companion.DEVICE_CHECK_ENDPOINT
import com.safetorun.api.DefaultSafeToRunApi.Companion.LOG_ENDPOINT
import com.safetorun.api.DefaultSafeToRunApi.Companion.VERIFY_CHECK_ENDPOINT
import com.safetorun.logger.models.SafeToRunEvents
import com.safetorun.models.models.ConfirmVerificationRequestDto
import com.safetorun.models.models.DataWrappedLogResponse
import com.safetorun.models.models.DataWrappedSignatureResult
import com.safetorun.models.models.DataWrappedVerifyResult
import com.safetorun.models.models.DeviceInformationDto
import com.safetorun.models.models.DeviceSignatureDto
import com.safetorun.models.models.VerifierResult
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase

internal class DefaultSafeToRunApiTest : TestCase() {
    private val mockHttpClient = mockk<SafeToRunHttpClient>(relaxed = true)
    private val defaultSafeToRunApi = DefaultSafeToRunApi(mockHttpClient, API_KEY)
    private val deviceSignatureDto = DeviceSignatureDto("signature")
    private val dataWrapperSignatureResult = DataWrappedSignatureResult(deviceSignatureDto)
    private val deviceSignatureVerification = DataWrappedVerifyResult(
        VerifierResult(
            correctIssuer = false,
            anyFailures = false,
            correctSignature = false,
            expired = false
        )
    )

    fun `test that call through has correct parameters for device check from client`() {

        // Given
        every {
            mockHttpClient.post(
                DEVICE_CHECK_ENDPOINT,
                mapOf(API_KEY_HEADER_NAME to API_KEY),
                deviceInformation,
                DeviceInformationDto.serializer(),
                DataWrappedSignatureResult.serializer()
            )
        } returns dataWrapperSignatureResult

        // When
        val result = defaultSafeToRunApi.postNewDevice(deviceInformation)

        // Then
        assertThat(result).isEqualTo(dataWrapperSignatureResult.data)
    }

    fun `test that when we call verify endpoint it returns expected result`() {
        // Given
        every {
            mockHttpClient.post(
                VERIFY_CHECK_ENDPOINT,
                mapOf(API_KEY_HEADER_NAME to API_KEY),
                any(),
                ConfirmVerificationRequestDto.serializer(),
                DataWrappedVerifyResult.serializer()
            )
        } returns deviceSignatureVerification

        // When
        val result = defaultSafeToRunApi.verifyDataResult(deviceSignatureDto)

        // Then
        assertThat(result).isEqualTo(deviceSignatureVerification.data)
    }

    fun `test that when we call log endpoint it returns expected result`() {
        // Given
        val serializer = DataWrappedLogResponse.serializer(SafeToRunEvents.serializer())

        every {
            mockHttpClient.post(
                LOG_ENDPOINT,
                mapOf(API_KEY_HEADER_NAME to API_KEY),
                any(),
                SafeToRunEvents.serializer(),
                serializer
            )
        } returns DataWrappedLogResponse(SafeToRunEvents.FailedCheck.empty("default"))

        // When
        defaultSafeToRunApi.logEvent(SafeToRunEvents.FailedCheck.empty("default"))

        // Then
        verify {
            mockHttpClient.post(
                LOG_ENDPOINT,
                mapOf(API_KEY_HEADER_NAME to API_KEY),
                any(),
                SafeToRunEvents.serializer(),
                any()
            )
        }
    }

    companion object {
        const val API_KEY = "FakeApiKey"
    }
}
