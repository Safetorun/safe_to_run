package com.safetorun.plus.builders

import com.google.common.truth.Truth.assertThat
import com.safetorun.features.signatureverify.SignatureVerificationQuery
import com.safetorun.plus.offdevice.builders.SignatureVerificationOffDeviceBuilder
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class SignatureVerificationOffDeviceBuilderTest : TestCase() {

    private val signatureVerificationQuery = mockk<SignatureVerificationQuery>()

    override fun setUp() {
        every { signatureVerificationQuery.retrieveSignatureForApplication() } returns SIGNATURE_SAMPLE
    }

    fun `test that signature verification query will return the expected signature`() {
        // Given
        val signatureVerificationOffDeviceBuilder =
            SignatureVerificationOffDeviceBuilder(
                signatureVerificationQuery
            )

        // When
        val result = signatureVerificationOffDeviceBuilder.buildOffDeviceResultBuilder(
            deviceInformationBuilder("")
        )

        // Then
        assertThat(result.build().signatureVerification.signatureVerificationString).isEqualTo(
            SIGNATURE_SAMPLE
        )
    }

    fun `test that signature verification query will return the expected signature when null`() {
        // Given
        every { signatureVerificationQuery.retrieveSignatureForApplication() } returns ""
        val signatureVerificationOffDeviceBuilder =
            SignatureVerificationOffDeviceBuilder(
                signatureVerificationQuery
            )

        // When
        val result = signatureVerificationOffDeviceBuilder.buildOffDeviceResultBuilder(
            deviceInformationBuilder("")
        )

        // Then
        assertThat(result.build().signatureVerification.signatureVerificationString).isEqualTo("")
    }

    companion object {
        const val SIGNATURE_SAMPLE = "signature sample"
    }
}
