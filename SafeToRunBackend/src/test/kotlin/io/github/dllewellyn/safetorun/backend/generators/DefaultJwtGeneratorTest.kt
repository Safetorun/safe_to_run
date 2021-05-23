package io.github.dllewellyn.safetorun.backend.generators

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.backend.repository.JwtSecretRepository
import io.github.dllewellyn.safetorun.models.models.SafeToRunResult
import io.github.dllewellyn.safetorun.backend.utils.DefaultExpireTimeHandler
import io.github.dllewellyn.safetorun.backend.utils.DefaultJwtFactory
import io.github.dllewellyn.safetorun.backend.utils.ExpireTimeHandler
import io.github.dllewellyn.safetorun.backend.verifiers.DefaultJwtVerifier
import io.github.dllewellyn.safetorun.backend.verifiers.JwtVerifier
import io.mockk.every
import io.mockk.mockk
import org.joda.time.DateTime
import org.joda.time.Duration
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class DefaultJwtGeneratorTest {

    private val expiryTime = mockk<ExpireTimeHandler>()

    @BeforeEach
    fun before() {
        every { expiryTime.getExpiryTime() } returns DefaultExpireTimeHandler().getExpiryTime()
    }

    @Test
    fun `test that a signature does not match if generated with a different signature`() {

        val result = generateForSecret()
            .generateSecretFor(SafeToRunResult.empty(ApiKeyOne, DeviceIdOne))
            .verify(verifierForSecret(SecretTwo, ApiKeyOne))

        assertThat(result.correctSignature).isFalse()
    }

    @Test
    fun `test that signature does match if with same signature`() {
        val result = generateForSecret()
            .generateSecretFor(SafeToRunResult.empty(ApiKeyOne, DeviceIdOne))
            .verify(verifierForSecret(SecretOne, ApiKeyOne))

        assertThat(result.correctSignature).isTrue()
    }

    @Test
    fun `test that verifier fails if API Key is not the same`() {
        val result = generateForSecret()
            .generateSecretFor(SafeToRunResult.empty(ApiKeyOne, DeviceIdOne))
            .verify(verifierForSecret(SecretTwo, ApiKeyTwo))

        assertThat(result.correctIssuer).isFalse()
    }

    @Test
    fun `test that verifier passes if device ID is  the same`() {
        val result = generateForSecret()
            .generateSecretFor(SafeToRunResult.empty(ApiKeyOne, DeviceIdOne))
            .verify(verifierForSecret(SecretOne, ApiKeyOne))

        assertThat(result.correctIssuer).isTrue()
    }

    @Test
    fun `test that verifier fails if there are failures`() {
        val result = generateForSecret()
            .generateSecretFor(SafeToRunResult.empty(ApiKeyOne, DeviceIdOne).copy(failures = 1))
            .verify(verifierForSecret(SecretOne, ApiKeyOne))

        assertThat(result.anyFailures).isTrue()
    }

    @Test
    fun `test that verifier passes if there are no failures`() {
        val result = generateForSecret()
            .generateSecretFor(SafeToRunResult.empty(ApiKeyOne, DeviceIdOne).copy(failures = 0))
            .verify(verifierForSecret(SecretOne, ApiKeyOne))

        assertThat(result.anyFailures).isFalse()
    }

    @Test
    fun `test that verifier fails if expired`() {
        every { expiryTime.getExpiryTime() } returns DateTime()
            .withDurationAdded(Duration.standardMinutes(10), -1)
            .toDate()

        val result = generateForSecret()
            .generateSecretFor(SafeToRunResult.empty(ApiKeyOne, DeviceIdOne).copy(failures = 1))
            .verify(verifierForSecret(SecretOne, ApiKeyOne))

        assertThat(result.expired).isTrue()
    }

    @Test
    fun `test that verifier passes if expired`() {
        val result = generateForSecret()
            .generateSecretFor(SafeToRunResult.empty(ApiKeyOne, DeviceIdOne).copy(failures = 0))
            .verify(verifierForSecret(SecretOne, ApiKeyOne))

        assertThat(result.expired).isFalse()
    }

    private fun generateForSecret() =
        DefaultJwtGenerator(DefaultJwtFactory(mockk<JwtSecretRepository>().apply {
            every { this@apply.getJwtSecret() } returns SecretOne
        }), expiryTime)

    private fun verifierForSecret(secret: String, apiKey: String) =
        DefaultJwtVerifier(apiKey, DefaultJwtFactory(mockk<JwtSecretRepository>().apply {
            every { this@apply.getJwtSecret() } returns secret
        }))

    companion object {
        const val SecretOne = "Secret"
        const val SecretTwo = "Secret2"

        const val ApiKeyOne = "ApiKey"
        const val ApiKeyTwo = "ApiKeyTwo"

        const val DeviceIdOne = "DeviceIdOne"
    }

    private fun String.verify(jwtVerifier: JwtVerifier) = jwtVerifier.verifyJwt(this)
}
