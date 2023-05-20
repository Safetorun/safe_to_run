package com.safetorun.offdevice

import android.content.Context
import com.google.common.truth.Truth.assertThat
import com.safetorun.api.SafeToRunApi
import com.safetorun.logger.models.AppMetadata
import com.safetorun.logger.models.DeviceInformation
import com.safetorun.logger.models.SafeToRunEvents
import com.safetorun.models.builders.DeviceInformationDtoBuilder
import com.safetorun.models.models.DeviceSignatureDto
import com.safetorun.models.builders.deviceInformationBuilder
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase

internal class AndroidSafeToRunOffDeviceTest : TestCase() {

    private val safeToRunApi = mockk<SafeToRunApi>()
    private val offDeviceResultBuilder = mockk<OffDeviceResultBuilder>()
    private val context = mockk<Context>()
    private val api: String = "Apikkey"
    private val deviceId: String = "DeviceId"

    private val androidSafeToRunOffDevice =
        AndroidSafeToRunOffDevice(safeToRunApi, offDeviceResultBuilder, api, deviceId)

    fun `test that when we call android safe to run it returns result from client`() {
        // Given
        val expectedResult = DeviceSignatureDto("signed result")
        every { offDeviceResultBuilder.buildOffDeviceResultBuilder(any()) } answers {
            (args[0] as DeviceInformationDtoBuilder).apply {
                dtoResult()
            }
        }
        every { safeToRunApi.postNewDevice(any()) } returns expectedResult

        // When
        val result = androidSafeToRunOffDevice.isSafeToRun()

        // Then
        assertThat(result).isEqualTo(SafeToRunOffDeviceResult(expectedResult.signature))
        verify { safeToRunApi.postNewDevice(matchingDto()) }
    }

    fun `test that when we call android safe to run twice with the same api key it is the same instance`() {
        // Given
        val safeToRun = context.safeToRunLogger("1234")
        val safeToRun2 = context.safeToRunLogger("1234")

        assertThat(safeToRun).isNotNull()
        assertThat(safeToRun2).isNotNull()
    }

    private fun matchingDto() = deviceInformationBuilder(api) {
        dtoResult()
        deviceId(deviceId)
    }

    private fun DeviceInformationDtoBuilder.dtoResult() {
        installOrigin("install")
        installedApplication("packagename")
        osVersion("osversion")
        manufacturer("manufacturer")
        signature("signature")
        model("model")
        board("board")
        bootloader("bootloader")
        device("device")
        hardware("hardware")
        host("host")
        cpuAbi("cpu")
        cpuAbi("cpuAbi")
    }
}
