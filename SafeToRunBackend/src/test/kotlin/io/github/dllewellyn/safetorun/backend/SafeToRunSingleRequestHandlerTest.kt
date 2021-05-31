package io.github.dllewellyn.safetorun.backend

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.SafeToRunLogic
import io.github.dllewellyn.safetorun.backend.builder.DefaultSafeToRunAbstractFactory
import io.github.dllewellyn.safetorun.backend.builder.JwtVerifierFactory
import io.github.dllewellyn.safetorun.backend.builder.SafeToRunAbstractFactory
import io.github.dllewellyn.safetorun.backend.handlers.SafeToRunRequestHandler
import io.github.dllewellyn.safetorun.backend.repository.JwtSecretRepository
import io.github.dllewellyn.safetorun.backend.util.easilyAcceptableModel
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import javax.inject.Inject

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SafeToRunSingleRequestHandlerTest {

    @Inject
    lateinit var deviceRequestHandler: SafeToRunRequestHandler

    @Inject
    lateinit var verifier: JwtVerifierFactory

    private val model = easilyAcceptableModel
    private val safeToRunAbstractFactory = mockk<SafeToRunAbstractFactory>()
    private val safeToRun = mockk<SafeToRunLogic>()

    @BeforeAll
    fun setup() {
        every { safeToRunAbstractFactory.generateSafeToRun(model) } returns safeToRun
    }

    @MockBean(DefaultSafeToRunAbstractFactory::class)
    fun mockBean(): SafeToRunAbstractFactory = safeToRunAbstractFactory

    @MockBean(JwtSecretRepository::class)
    fun mockJwtSecretRepository(): JwtSecretRepository = mockk<JwtSecretRepository>().apply {
        every { this@apply.getJwtSecret() } returns "ThiisAMockSecret"
    }

    @Test
    fun `test that the result of execution is correct`() {
        // Given
        every { safeToRun.isSafeToRun() } returns SafeToRunReport.MultipleReports(
            listOf(
                SafeToRunReport.SafeToRunReportSuccess("")
            )
        )

        // When
        val deviceResponse = deviceRequestHandler.execute(model)

        // Then
        with(verifier.verifierForApiKey(model.apiKey).verifyJwt(deviceResponse.signature)) {
            assertThat(anyFailures).isFalse()
            assertThat(correctIssuer).isTrue()
        }
    }
}
