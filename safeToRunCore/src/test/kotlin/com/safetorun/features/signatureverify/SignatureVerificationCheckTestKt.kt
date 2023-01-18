package com.safetorun.features.signatureverify

import com.google.common.truth.Truth
import com.safetorun.reporting.SafeToRunReport
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class SignatureVerificationCheckTestKt : TestCase() {
    private val mockQuery = mockk<SignatureVerificationQuery>()

    fun `test that we verify a signature given expected input when we fail`() {
        // Given
        val mockStrings = mockk<SignatureVerificationStrings>().apply {
            every { signatureNotMatchedMessage(any()) } answers {
                String.format(FAILURE_MESSAGE, args[0].toStr().removeSuffix("[").removeSuffix("]"))
            }
        }

        every { mockQuery.retrieveSignatureForApplication() } returns SIGNATURE

        // When
        val reportResult = verifySignatureConfiguration(
            mockStrings,
            mockQuery,
            "abc",
        ).canRun() as SafeToRunReport.SafeToRunReportFailure

        // Then
        Truth.assertThat(reportResult.failureMessage).contains(SIGNATURE)
    }

    fun `test that we verify a signature given expected input`() {
        // Given
        val mockStrings = mockk<SignatureVerificationStrings>().apply {
            every { signatureMatchesMessage() } returns SUCCESS_MESSAGE
        }

        every { mockQuery.retrieveSignatureForApplication() } returns SIGNATURE

        // When
        val reportResult =
            verifySignatureConfiguration(
                mockStrings,
                mockQuery,
                SIGNATURE,
            )
                .canRun() as SafeToRunReport.SafeToRunReportSuccess

        // Then
        Truth.assertThat(reportResult.successMessage).isEqualTo(SUCCESS_MESSAGE)
    }

    companion object {
        private const val SUCCESS_MESSAGE = "Success"
        private const val FAILURE_MESSAGE = "Failure message %s"
        private const val NOT_FOUND_MESSAGE = "Not found "
        private const val SIGNATURE = "WgtvIJ0A//RKGHShVDQRPdd/9ks="
    }
}
