package io.github.dllewellyn.safetorun.backend.services

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.backend.builder.JwtVerifierFactory
import io.github.dllewellyn.safetorun.models.models.ConfirmVerificationRequestDto
import io.github.dllewellyn.safetorun.backend.verifiers.JwtVerifier
import io.github.dllewellyn.safetorun.models.models.VerifierResult
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class SafeToRunVerificationServiceTest {

    private val jwtVerifierFactory = mockk<JwtVerifierFactory>()
    private val jwtVerifier = mockk<JwtVerifier>()

    private val safeToRunVerificationService = DefaultSafeToRunVerificationService(jwtVerifierFactory)

    private val signature = ConfirmVerificationRequestDto().apply {
        signature = "Abc"
        apiKey = "ApiKey"
    }

    @Test
    fun `test that calling safe to run will call through to verifier`() {
        // Given
        val verifierResult = VerifierResult(
            true,
            anyFailures = true,
            correctSignature = true,
            expired = true
        )

        every { jwtVerifierFactory.verifierForApiKey(signature.apiKey) } returns jwtVerifier
        every { jwtVerifier.verifyJwt(signature.signature) } returns verifierResult

        // When
        val result = safeToRunVerificationService.verify(signature)

        // Then
        assertThat(result).isEqualTo(verifierResult)
    }
}
