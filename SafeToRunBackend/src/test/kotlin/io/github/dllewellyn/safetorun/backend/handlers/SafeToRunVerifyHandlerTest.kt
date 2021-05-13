package io.github.dllewellyn.safetorun.backend.handlers

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.backend.models.ConfirmVerificationRequestDto
import io.github.dllewellyn.safetorun.backend.services.SafeToRunVerificationService
import io.github.dllewellyn.safetorun.backend.verifiers.VerifierResult
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import javax.inject.Inject

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SafeToRunVerifyHandlerTest {

    private val safeToRunService = mockk<SafeToRunVerificationService>()

    @Inject
    private val safeToRunVerify = SafeToRunVerifyHandler()

    @BeforeAll
    fun before() {
        safeToRunVerify.safeToRun = safeToRunService
    }

    @Test
    fun `test that service calls through to verify`() {
        // Given
        val signature = ConfirmVerificationRequestDto().apply {
            signature = "Abc"
            apiKey = "ApiKey"
        }

        val verifierResult =
            VerifierResult(correctIssuer = true, anyFailures = true, correctSignature = true, expired = true)
        every { safeToRunService.verify(signature) } returns verifierResult

        // When
        val result = safeToRunVerify.execute(signature)

        // Then
        assertThat(result).isEqualTo(verifierResult)
    }
}
