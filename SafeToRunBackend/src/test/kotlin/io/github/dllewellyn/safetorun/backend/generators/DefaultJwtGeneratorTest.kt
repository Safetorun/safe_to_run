package io.github.dllewellyn.safetorun.backend.generators

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.backend.repository.JwtSecretRepository
import io.github.dllewellyn.safetorun.backend.util.DummyJwtSecretRepository
import io.github.dllewellyn.safetorun.backend.utils.DefaultExpireTimeHandler
import io.github.dllewellyn.safetorun.backend.utils.DefaultJwtFactory
import io.github.dllewellyn.safetorun.backend.utils.ExpireTimeHandler
import io.github.dllewellyn.safetorun.backend.verifiers.DefaultJwtVerifier
import io.github.dllewellyn.safetorun.backend.verifiers.JwtVerifier
import io.github.dllewellyn.safetorun.models.models.SafeToRunResult
import io.micronaut.test.annotation.MockBean
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
    private val verifier = DummyJwtSecretRepository()

    @BeforeEach
    fun before() {
        every { expiryTime.getExpiryTime() } returns DefaultExpireTimeHandler().getExpiryTime()
    }

    @MockBean(JwtSecretRepository::class)
    fun mockBean(): JwtSecretRepository = DummyJwtSecretRepository()

    @Test
    fun `test that a signature does not match if generated with a different signature`() {

        val result = generateForSecret()
            .generateSecretFor(SafeToRunResult.empty(ApiKeyOne, DeviceIdOne))
            .verify(verifierForSecret(ApiKeyOne, DummyJwtSecretRepository()))

        assertThat(result.correctSignature).isFalse()
    }

    @Test
    fun `test that signature does match if with same signature`() {
        val result = generateForSecret()
            .generateSecretFor(SafeToRunResult.empty(ApiKeyOne, DeviceIdOne))
            .verify(verifierForSecret(ApiKeyOne))

        assertThat(result.correctSignature).isTrue()
    }

    @Test
    fun `test that verifier fails if API Key is not the same`() {
        val result = generateForSecret()
            .generateSecretFor(SafeToRunResult.empty(ApiKeyOne, DeviceIdOne))
            .verify(verifierForSecret(ApiKeyTwo))

        assertThat(result.correctIssuer).isFalse()
    }

    @Test
    fun `test that verifier passes if device ID is  the same`() {
        val result = generateForSecret()
            .generateSecretFor(SafeToRunResult.empty(ApiKeyOne, DeviceIdOne))
            .verify(verifierForSecret(ApiKeyOne))

        assertThat(result.correctIssuer).isTrue()
    }

    @Test
    fun `test that verifier fails if there are failures`() {
        val result = generateForSecret()
            .generateSecretFor(SafeToRunResult.empty(ApiKeyOne, DeviceIdOne).copy(failures = 1))
            .verify(verifierForSecret(ApiKeyOne))

        assertThat(result.anyFailures).isTrue()
    }

    @Test
    fun `test that verifier passes if there are no failures`() {
        val result = generateForSecret()
            .generateSecretFor(SafeToRunResult.empty(ApiKeyOne, DeviceIdOne).copy(failures = 0))
            .verify(verifierForSecret(ApiKeyOne))

        assertThat(result.anyFailures).isFalse()
    }

    @Test
    fun `test that verifier fails if expired`() {
        every { expiryTime.getExpiryTime() } returns DateTime()
            .withDurationAdded(Duration.standardMinutes(10), -1)
            .toDate()

        val result = generateForSecret()
            .generateSecretFor(SafeToRunResult.empty(ApiKeyOne, DeviceIdOne).copy(failures = 1))
            .verify(verifierForSecret(ApiKeyOne))

        assertThat(result.expired).isTrue()
    }

    @Test
    fun `test that verifier passes if expired`() {
        val result = generateForSecret()
            .generateSecretFor(SafeToRunResult.empty(ApiKeyOne, DeviceIdOne).copy(failures = 0))
            .verify(verifierForSecret(ApiKeyOne))

        assertThat(result.expired).isFalse()
    }

    private fun generateForSecret() =
        DefaultJwtGenerator(DefaultJwtFactory(verifier), expiryTime)

    private fun verifierForSecret(apiKey: String, v: JwtSecretRepository = verifier) =
        DefaultJwtVerifier(apiKey, DefaultJwtFactory(v))

    companion object {
        const val ApiKeyOne = "ApiKey"
        const val ApiKeyTwo = "ApiKeyTwo"

        const val DeviceIdOne = "DeviceIdOne"
    }

    private fun String.verify(jwtVerifier: JwtVerifier) = jwtVerifier.verifyJwt(this)
}
