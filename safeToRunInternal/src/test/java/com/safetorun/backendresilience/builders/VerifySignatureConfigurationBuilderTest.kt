package com.safetorun.backendresilience.builders

import com.google.common.truth.Truth.assertThat
import com.safetorun.resilienceshared.dto.Severity
import org.junit.Test

internal class VerifySignatureConfigurationBuilderTest {

    @Test
    fun `test adding verify signature configuration with signatures`() {
        val configuration = VerifySignatureConfigurationOffDeviceBuilder(Severity.Error)
            .apply {
                +SIGNATURE_A
                SIGNATURE_B.allowSignature()
            }
            .build()

        assertThat(configuration.allowedSignatures).containsAtLeast(SIGNATURE_A, SIGNATURE_B)
        assertThat(configuration.severity).isEqualTo(Severity.Error)
    }

    @Test
    fun `test adding verify signature configuration with no signatures`() {
        val configuration = VerifySignatureConfigurationOffDeviceBuilder(Severity.Warn)
            .build()

        assertThat(configuration.severity).isEqualTo(Severity.Warn)
    }

    companion object {
        const val SIGNATURE_A = "abcdef"
        const val SIGNATURE_B = "asdasd"
    }
}
