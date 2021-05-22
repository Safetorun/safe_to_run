package io.github.dllewellyn.safetorun.backend.features.signature

import com.google.common.truth.Truth.assertThat
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class OffDeviceSignatureVerificationStringsTest {
    @Inject
    lateinit var signatureVerificationStrings: OffDeviceSignatureVerificationStrings

    @Test
    fun `test that strings are as expected`() {
        assertThat(signatureVerificationStrings.signatureMatchesMessage()).isNotEmpty()
        assertThat(signatureVerificationStrings.signatureNotFoundMessage()).isNotEmpty()
        assertThat(signatureVerificationStrings.signatureNotMatchedMessage("Abc")).contains("Abc")
    }
}
