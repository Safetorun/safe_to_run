package io.github.dllewellyn.safetorun.backend

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.SafeToRun
import io.github.dllewellyn.safetorun.backend.builder.DefaultSafeToRunAbstractFactory
import io.github.dllewellyn.safetorun.backend.builder.JwtVerifierFactory
import io.github.dllewellyn.safetorun.backend.builder.SafeToRunAbstractFactory
import io.github.dllewellyn.safetorun.backend.handlers.SafeToRunRequestHandler
import io.github.dllewellyn.safetorun.backend.models.BlacklistedAppsDto
import io.github.dllewellyn.safetorun.backend.models.DeviceInformationDto
import io.github.dllewellyn.safetorun.backend.models.InstallOriginDto
import io.github.dllewellyn.safetorun.backend.models.OsCheckDto
import io.github.dllewellyn.safetorun.backend.models.SignatureVerification
import io.github.dllewellyn.safetorun.features.installorigin.GooglePlayStore
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*
import javax.inject.Inject

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SafeToRunSingleRequestHandlerTest {

    private val easilyAcceptableModel = DeviceInformationDto().apply {
        apiKey = ApiKey
        deviceId = DeviceId
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
            signatureVerification = ""
        }
    }

    @Inject
    lateinit var deviceRequestHandler: SafeToRunRequestHandler

    @Inject
    lateinit var verifier: JwtVerifierFactory

    private val safeToRunAbstractFactory = mockk<SafeToRunAbstractFactory>()
    private val safeToRun = mockk<SafeToRun>()

    @BeforeAll
    fun setup() {
        every { safeToRunAbstractFactory.generateSafeToRun(easilyAcceptableModel) } returns safeToRun
    }

    @MockBean(DefaultSafeToRunAbstractFactory::class)
    fun mockBean(): SafeToRunAbstractFactory = safeToRunAbstractFactory

    @Test
    fun `test that the result of execution is correct`() {
        // Given
        every { safeToRun.isSafeToRun() } returns SafeToRunReport.MultipleReports(
            listOf(
                SafeToRunReport.SafeToRunReportSuccess("")
            )
        )

        // When
        val deviceResponse = deviceRequestHandler.execute(easilyAcceptableModel)

        // Then
        with(verifier.verifierForApiKey(easilyAcceptableModel.apiKey).verifyJwt(deviceResponse.signature)) {
            assertThat(anyFailures).isFalse()
            assertThat(correctIssuer).isTrue()
        }
    }

    companion object {
        private const val ApiKey = "ApiKey"
        private val DeviceId = UUID.randomUUID().toString()
    }
}