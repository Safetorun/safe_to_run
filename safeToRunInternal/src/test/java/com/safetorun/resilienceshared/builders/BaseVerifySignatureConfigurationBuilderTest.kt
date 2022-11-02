package com.safetorun.resilienceshared.builders

import com.google.common.truth.Truth
import org.junit.Test

internal class BaseVerifySignatureConfigurationBuilderTest {
    @Test
    fun `test adding verify signature configuration with signatures`() {
        val configuration = BaseVerifySignatureConfigurationBuilder()
            .apply {
                +SIGNATURE_A
                SIGNATURE_B.allowSignature()
            }
            .build()

        Truth.assertThat(configuration.allowedSignatures).containsAtLeast(SIGNATURE_A, SIGNATURE_B)
    }

    @Test
    fun `test adding verify signature configuration with no signatures`() {
        val configuration = BaseVerifySignatureConfigurationBuilder()
            .build()

        Truth.assertThat(configuration.allowedSignatures).isEmpty()
    }

    companion object {
        const val SIGNATURE_A = "abcdef"
        const val SIGNATURE_B = "asdasd"
    }
}
