package io.github.dllewellyn.safetorun.backend.services

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.SafeToRunLogic
import io.github.dllewellyn.safetorun.backend.builder.SafeToRunAbstractFactory
import io.github.dllewellyn.safetorun.backend.generators.JwtGenerator
import io.github.dllewellyn.safetorun.features.installorigin.GooglePlayStore
import io.github.dllewellyn.safetorun.models.models.BlacklistedAppsDto
import io.github.dllewellyn.safetorun.models.models.DeviceInformationDto
import io.github.dllewellyn.safetorun.models.models.InstallOriginDto
import io.github.dllewellyn.safetorun.models.models.OsCheckDto
import io.github.dllewellyn.safetorun.models.models.SafeToRunResult
import io.github.dllewellyn.safetorun.models.models.SignatureVerification
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class SafeToRunServiceTest {

    private val safeToRunTokenGenerator = mockk<JwtGenerator>()
    private val safeToRunAbstractFactory = mockk<SafeToRunAbstractFactory>()
    private val safeToRunMock = mockk<SafeToRunLogic>()
    private val safeToRunService = SafeToRunService(safeToRunTokenGenerator, safeToRunAbstractFactory)

    private val easilyAcceptableModel = DeviceInformationDto().apply {
        apiKey = "ApiKey"
        deviceId = "DeviceId"
        blacklistedApp = BlacklistedAppsDto().apply {
            installedPackages = emptyList()
        }
        installOrigin = InstallOriginDto().apply {
            installOriginPackageName = GooglePlayStore().originPackage
        }
        osCheck = OsCheckDto().apply {
            osVersion = "31"
            manufacturer = "Google"
        }
        signatureVerification = SignatureVerification().apply {
            signatureVerificationString = ""
        }
    }

    @Test
    fun `test that safe to run service will convert and call through to generator`() {
        // Given
        val successfulString = "Success"
        val expectedScanResult = SafeToRunResult(
            failures = 2,
            successes = 2,
            warnings = 1,
            easilyAcceptableModel.apiKey,
            easilyAcceptableModel.deviceId
        )

        every { safeToRunAbstractFactory.generateSafeToRun(easilyAcceptableModel) } returns safeToRunMock
        every { safeToRunMock.isSafeToRun() } returns SafeToRunReport.MultipleReports(
            listOf(
                SafeToRunReport.SafeToRunReportFailure("Abc", ""),
                SafeToRunReport.SafeToRunReportSuccess("Abc"),
                SafeToRunReport.SafeToRunReportSuccess("Def"),
                SafeToRunReport.SafeToRunWarning("Def", "Hij"),
                SafeToRunReport.MultipleReports(
                    listOf(
                        SafeToRunReport.SafeToRunReportFailure("Lmno", ""),
                    )
                )
            )
        )

        every { safeToRunTokenGenerator.generateSecretFor(expectedScanResult) } returns successfulString

        // When
        val result = safeToRunService.generateResponseTokenForRequest(easilyAcceptableModel)

        // Then
        assertThat(result).isEqualTo(successfulString)
    }
}
