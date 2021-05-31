package io.github.dllewellyn.safetorun.offdevice.builders

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.features.signatureverify.SignatureVerificationQuery
import io.github.dllewellyn.safetorun.models.models.deviceInformationBuilder
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
        val signatureVerificationOffDeviceBuilder = SignatureVerificationOffDeviceBuilder(signatureVerificationQuery)

        // When
        val result = signatureVerificationOffDeviceBuilder.buildOffDeviceResultBuilder(deviceInformationBuilder(""))

        // Then
        assertThat(result.buildPartial().signatureVerification.signatureVerificationString).isEqualTo(SIGNATURE_SAMPLE)
    }

    fun `test that signature verification query will return the expected signature when null`() {
        // Given
        every { signatureVerificationQuery.retrieveSignatureForApplication() } returns null
        val signatureVerificationOffDeviceBuilder = SignatureVerificationOffDeviceBuilder(signatureVerificationQuery)

        // When
        val result = signatureVerificationOffDeviceBuilder.buildOffDeviceResultBuilder(deviceInformationBuilder(""))

        // Then
        assertThat(result.buildPartial().signatureVerification.signatureVerificationString).isNull()
    }

    companion object {
        const val SIGNATURE_SAMPLE = "signature sample"
    }
}
