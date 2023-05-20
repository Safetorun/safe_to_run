package com.safetorun.offdevice

import android.content.Context
import com.google.common.truth.Truth.assertThat
import com.safetorun.api.SafeToRunApi
import com.safetorun.logger.models.BlacklistedApps
import com.safetorun.logger.models.DeviceInformation
import com.safetorun.logger.models.DeviceSignature
import com.safetorun.logger.models.InstallOrigin
import com.safetorun.logger.models.OsCheck
import com.safetorun.models.builders.DeviceInformationDtoBuilder
import com.safetorun.models.builders.deviceInformationBuilder
import com.safetorun.models.models.BlacklistedAppsDto
import com.safetorun.models.models.DeviceInformationDto
import com.safetorun.models.models.DeviceSignatureDto
import com.safetorun.models.models.InstallOriginDto
import com.safetorun.models.models.OsCheckDto
import com.safetorun.models.models.SignatureVerificationDto
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

    private val installOriginDto = InstallOriginDto(installOriginPackageName = "com.example.app")
    private val blacklistedAppsDto =
        BlacklistedAppsDto(installedPackages = listOf("com.example.blacklisted"))
    private val signatureVerificationDto = DeviceSignatureDto(signature = "signatureString")
    private val isRooted = false


    private val androidSafeToRunOffDevice =
        AndroidSafeToRunOffDevice(safeToRunApi, offDeviceResultBuilder, api, deviceId)

    private val osCheckDto = OsCheckDto(
        osVersion = "Android 10",
        manufacturer = "Samsung",
        model = "Galaxy S10",
        board = "exynos",
        bootloader = "BLv3",
        cpuAbi = listOf("arm64-v8a"),
        host = "buildhost",
        hardware = "samsungexynos9820",
        device = "beyond1q"
    )


    fun `test that os check can convert`() {
        val expectedOsCheck = OsCheck(
            osVersion = "Android 10",
            manufacturer = "Samsung",
            model = "Galaxy S10",
            board = "exynos",
            bootloader = "BLv3",
            cpuAbi = listOf("arm64-v8a"),
            host = "buildhost",
            hardware = "samsungexynos9820",
            device = "beyond1q"
        )

        val actualOsCheck = osCheckDto.toOsCheck()

        assertThat(actualOsCheck).isEqualTo(expectedOsCheck)
    }

    fun `test that device configuration can be converted to the right object`() {
        val deviceInformationDto = DeviceInformationDto(
            osCheck = osCheckDto,
            installOrigin = installOriginDto,
            blacklistedApp = blacklistedAppsDto,
            signatureVerification = SignatureVerificationDto(signatureVerificationDto.signature),
            isRooted = isRooted
        )

        val expectedDeviceInformation = DeviceInformation(
            osCheck = OsCheck(
                osVersion = "Android 10",
                manufacturer = "Samsung",
                model = "Galaxy S10",
                board = "exynos",
                bootloader = "BLv3",
                cpuAbi = listOf("arm64-v8a"),
                host = "buildhost",
                hardware = "samsungexynos9820",
                device = "beyond1q"
            ),
            installOrigin = InstallOrigin("com.example.app"),
            blacklistedApp = BlacklistedApps(listOf("com.example.blacklisted")),
            signatureVerification = DeviceSignature("signatureString"),
            isRooted = false
        )

        val actualDeviceInformation = deviceInformationDto.toDeviceInformation()

        assertThat(actualDeviceInformation).isEqualTo(expectedDeviceInformation)
    }

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
