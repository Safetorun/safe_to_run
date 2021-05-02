package io.github.dllewellyn.safetorun.features.signatureverify

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.content.pm.SigningInfo
import android.util.Base64
import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase


internal class SignatureVerificationCheckTest : TestCase() {
    private val mockContext = mockk<Context>()
    private val mockPackageManager = mockk<PackageManager>()
    private val mockPackage = mockk<PackageInfo>()
    private val signatureInfo = mockk<SigningInfo>()
    private val signature = mockk<Signature>()
    private val sdkVersion = 30

    override fun setUp() {
        MockKAnnotations.init(this)
        every { mockContext.packageManager } returns mockPackageManager
        every { mockContext.packageName } returns OUR_PACKAGE
        every {
            mockPackageManager.getPackageInfo(
                OUR_PACKAGE,
                any()
            )
        } returns mockPackage

        mockPackage.signingInfo = signatureInfo
        mockPackage.signatures = arrayOf(signature)
        every { signatureInfo.apkContentsSigners } returns arrayOf(signature)
        every { signature.toByteArray() } returns "fake signature".toByteArray()
        mockkStatic(Base64::class)
        every { Base64.encodeToString(any(), any()) } answers {
            java.util.Base64.getEncoder().encodeToString(args[0] as ByteArray)
        }
    }

    fun `test that we verify a signature given expected input when we fail`() {
        // Given
        val mockStrings = mockk<SignatureVerificationStrings>().apply {
            every { signatureNotMatchedMessage(any()) } answers {
                String.format(FAILURE_MESSAGE, args[0].toStr().removeSuffix("[").removeSuffix("]"))
            }
        }

        // When
        val reportResult = SignatureVerificationCheck(
            listOf("abc"),
            mockStrings,
            AndroidSignatureVerificationQuery(mockContext, sdkVersion),
        )
            .canRun() as SafeToRunReport.SafeToRunReportFailure

        // Then
        assertThat(reportResult.failureMessage).contains(SIGNATURE)
    }

    fun `test that we verify a signature given expected input`() {
        // Given
        val mockStrings = mockk<SignatureVerificationStrings>().apply {
            every { signatureMatchesMessage() } returns SUCCESS_MESSAGE
        }
        // When
        val reportResult =
            SignatureVerificationCheck(listOf(SIGNATURE),
                mockStrings,
                AndroidSignatureVerificationQuery(mockContext, sdkVersion),
            )
                .canRun() as SafeToRunReport.SafeToRunReportSuccess

        // Then
        assertThat(reportResult.successMessage).isEqualTo(SUCCESS_MESSAGE)
    }

    fun `test that we fail a signature if it is not possible to generate`() {
        // Given
        val mockStrings = mockk<SignatureVerificationStrings>().apply {
            every { signatureNotFoundMessage() } returns NOT_FOUND_MESSAGE
        }

        every { signature.toByteArray() } returns null

        // When
        val reportResult =
            SignatureVerificationCheck(listOf(SIGNATURE),
                mockStrings,
                AndroidSignatureVerificationQuery(mockContext, sdkVersion),
            )
                .canRun() as SafeToRunReport.SafeToRunReportFailure

        // Then
        assertThat(reportResult.failureMessage).isEqualTo(NOT_FOUND_MESSAGE)
    }

    fun `test that we verify a signature given expected input when we fail_lower_sdk`() {
        // Given
        val mockStrings = mockk<SignatureVerificationStrings>().apply {
            every { signatureNotMatchedMessage(any()) } answers {
                String.format(FAILURE_MESSAGE, args[0].toStr().removeSuffix("[").removeSuffix("]"))
            }
        }

        // When
        val reportResult = SignatureVerificationCheck(listOf("abc"),
            mockStrings,
            AndroidSignatureVerificationQuery(mockContext, 27),
        )
            .canRun() as SafeToRunReport.SafeToRunReportFailure

        // Then
        assertThat(reportResult.failureMessage).contains(SIGNATURE)
    }

    fun `test that we verify a signature given expected input_lower_sdk`() {
        // Given
        val mockStrings = mockk<SignatureVerificationStrings>().apply {
            every { signatureMatchesMessage() } returns SUCCESS_MESSAGE
        }

        // When
        val reportResult =
            SignatureVerificationCheck(listOf(SIGNATURE),
                mockStrings,
                AndroidSignatureVerificationQuery(mockContext, 27),
            )
                .canRun() as SafeToRunReport.SafeToRunReportSuccess

        // Then
        assertThat(reportResult.successMessage).isEqualTo(SUCCESS_MESSAGE)
    }

    fun `test that we fail a signature if it is not possible to generate_lower_sdk`() {
        // Given
        val mockStrings = mockk<SignatureVerificationStrings>().apply {
            every { signatureNotFoundMessage() } returns NOT_FOUND_MESSAGE
        }

        every { signature.toByteArray() } returns null

        // When
        val reportResult =
            SignatureVerificationCheck(listOf(SIGNATURE),
                mockStrings,
                AndroidSignatureVerificationQuery(mockContext, 27),
            )
                .canRun() as SafeToRunReport.SafeToRunReportFailure

        // Then
        assertThat(reportResult.failureMessage).isEqualTo(NOT_FOUND_MESSAGE)
    }

    companion object {
        private const val OUR_PACKAGE = "com.abc.def"
        private const val SUCCESS_MESSAGE = "Success"
        private const val FAILURE_MESSAGE = "Failure message %s"
        private const val NOT_FOUND_MESSAGE = "Not found "
        private const val SIGNATURE = "WgtvIJ0A//RKGHShVDQRPdd/9ks="
    }
}