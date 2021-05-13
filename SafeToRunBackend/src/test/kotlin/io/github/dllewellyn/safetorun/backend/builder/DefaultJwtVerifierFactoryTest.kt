package io.github.dllewellyn.safetorun.backend.builder

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.backend.utils.DefaultJwtFactory
import org.junit.jupiter.api.Test

internal class DefaultJwtVerifierFactoryTest {

    private val defaultJwtVerifierFactory = DefaultJwtVerifierFactory(DefaultJwtFactory(""))

    @Test
    fun `verifier for key will get a verifier`() {
        assertThat(defaultJwtVerifierFactory.verifierForApiKey("ApiKey"))
            .isNotNull()
    }
}
