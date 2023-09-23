package com.safetorun.offdevice

import android.content.Context
import android.content.pm.PackageManager
import com.google.common.truth.Truth.assertThat
import com.safetorun.api.DefaultSafeToRunApi
import com.safetorun.api.SafeToRunApi
import com.safetorun.logger.models.BlacklistedApps
import com.safetorun.logger.models.DeviceInformation
import com.safetorun.logger.models.DeviceSignature
import com.safetorun.logger.models.InstallOrigin
import com.safetorun.logger.models.OsCheck
import com.safetorun.logger.models.SafeToRunEvents
import com.safetorun.models.builders.DeviceInformationDtoBuilder
import com.safetorun.models.builders.deviceInformationBuilder
import com.safetorun.models.models.DataWrappedLogResponse
import com.safetorun.models.models.DeviceSignatureDto
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import junit.framework.TestCase
import kotlinx.serialization.json.Json
import org.mockserver.integration.ClientAndServer
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse
import kotlin.random.Random

internal class AndroidSafeToRunOffDeviceTest : TestCase() {

    private val safeToRunApi = mockk<SafeToRunApi>()
    private val offDeviceResultBuilder = mockk<OffDeviceResultBuilder>()
    private val context = mockk<Context>()
    private val api: String = "Apikkey"
    private val deviceId: String = "DeviceId"

    private val androidSafeToRunOffDevice =
        AndroidSafeToRunOffDevice(safeToRunApi, offDeviceResultBuilder, api, deviceId)

    private val port = Random.nextInt(9000, 9999)
    private val mockServer: ClientAndServer by lazy { ClientAndServer.startClientAndServer(port) }
    private val url: String = "http://localhost:$port"

    override fun setUp() {
        mockkStatic("com.safetorun.offdevice.AndroidSafeToRunOffDeviceKt")
        every {
            context.offDeviceResultBuilder(
                enableInstalledPackageScan = true,
                rootCheck = false
            )
        } returns OffDeviceResultBuilder {
            it
        }
//        context.setupMocks()
        every { context.packageManager } returns mockk<PackageManager>(relaxed = true).apply {
            every {
                getPackageInfo(
                    any<String>(),
                    any<PackageManager.PackageInfoFlags>()
                )
            } returns mockk(
                relaxed = true
            )
        }
    }

    fun `test that os check can convert`() {
        val expectedOsCheck = OsCheck(
            osVersion = matchingDto().osCheck.osVersion,
            manufacturer = matchingDto().osCheck.manufacturer,
            model = matchingDto().osCheck.model,
            board = matchingDto().osCheck.board,
            bootloader = matchingDto().osCheck.bootloader,
            cpuAbi = matchingDto().osCheck.cpuAbi,
            host = matchingDto().osCheck.host,
            hardware = matchingDto().osCheck.hardware,
            device = matchingDto().osCheck.device
        )

        val actualOsCheck = matchingDto().osCheck.toOsCheck()

        assertThat(actualOsCheck).isEqualTo(expectedOsCheck)
    }

    fun `test that device configuration can be converted to the right object`() {
        val expectedDeviceInformation = DeviceInformation(
            osCheck = OsCheck(
                osVersion = matchingDto().osCheck.osVersion,
                manufacturer = matchingDto().osCheck.manufacturer,
                model = matchingDto().osCheck.model,
                board = matchingDto().osCheck.board,
                bootloader = matchingDto().osCheck.bootloader,
                cpuAbi = matchingDto().osCheck.cpuAbi,
                host = matchingDto().osCheck.host,
                hardware = matchingDto().osCheck.hardware,
                device = matchingDto().osCheck.device
            ),
            installOrigin = InstallOrigin(matchingDto().installOrigin.installOriginPackageName!!),
            blacklistedApp = BlacklistedApps(matchingDto().blacklistedApp.installedPackages),
            signatureVerification = DeviceSignature(matchingDto().signatureVerification.signatureVerificationString!!),
            isRooted = false
        )

        val actualDeviceInformation = matchingDto().toDeviceInformation()

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
        val apiKey = "1234"
        val safeToRun = context.safeToRunLogger(
            apiKey,
            url,
            enableInstalledPackageScan = true,
            rootCheck = false
        )
        val safeToRun2 = context.safeToRunLogger(
            apiKey,
            url,
            enableInstalledPackageScan = true,
            rootCheck = false
        )

        assertThat(safeToRun).isNotNull()
        assertThat(safeToRun2).isNotNull()

        mockServer.`when`(
            HttpRequest.request()
                .withHeader("x-api-key", apiKey)
                .withPath(DefaultSafeToRunApi.LOG_ENDPOINT)
        ).respond(
            HttpResponse.response()
                .withStatusCode(200)
                .withBody(responseBody(SafeToRunEvents.SucceedCheck.empty("default")))
        )

        safeToRun.invoke(
            SafeToRunEvents.SucceedCheck.empty("default")
        )
    }

    private fun responseBody(event: SafeToRunEvents) = Json.encodeToString(
        DataWrappedLogResponse.serializer(SafeToRunEvents.serializer()),
        DataWrappedLogResponse(event),
    )

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

    override fun tearDown() {
        mockServer.close()
    }

}
