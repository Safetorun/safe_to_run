package io.github.dllewellyn.safetorun.backend.handlers

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.backend.repository.JwtSecretRepository
import io.github.dllewellyn.safetorun.backend.repository.SafeToRunConfigurationRepository
import io.github.dllewellyn.safetorun.models.models.ConfirmVerificationRequestDto
import io.github.dllewellyn.safetorun.backend.services.SafeToRunVerificationService
import io.github.dllewellyn.safetorun.backend.util.mockkSecretManager
import io.github.dllewellyn.safetorun.models.models.VerifierResult
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import javax.inject.Inject

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Disabled
internal class SafeToRunVerifyHandlerTest {

    private val safeToRunService = mockk<SafeToRunVerificationService>()

    @Inject
    private val safeToRunVerify = SafeToRunVerifyHandler()

    @BeforeAll
    fun before() {
        safeToRunVerify.safeToRun = safeToRunService
    }

    @MockBean(JwtSecretRepository::class)
    fun mockSecretRepository(): JwtSecretRepository = mockkSecretManager

    @MockBean(SafeToRunConfigurationRepository::class)
    fun mockSafeToRunConfigurationRepository() : SafeToRunConfigurationRepository = mockk()

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

    @Test
    fun `test an exception is thrown if the signature is empty`() {
        assertThrows<IllegalArgumentException> { safeToRunVerify.execute(ConfirmVerificationRequestDto()) }
    }
}
