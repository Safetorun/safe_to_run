package com.andro.safetorun.features.signatureverify

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.content.pm.SigningInfo
import android.content.res.Resources
import android.util.Base64
import com.andro.safetorun.R
import com.andro.safetorun.reporting.SafeToRunReport
import com.google.common.truth.Truth.assertThat
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase


internal class SignatureVerificationCheckTest : TestCase() {

    @MockK
    private lateinit var mockContext: Context

    private val mockPackageManager = mockk<PackageManager>()
    private val mockPackage = mockk<PackageInfo>()
    private val signatureInfo = mockk<SigningInfo>()
    private val signature = mockk<Signature>()
    private val sdkVersion = 30
    private val mockResources = mockk<Resources>()

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
        every { mockContext.resources } returns mockResources
        mockkStatic(Base64::class)
        every { Base64.encodeToString(any(), any()) } answers {
            java.util.Base64.getEncoder().encodeToString(args[0] as ByteArray)
        }
    }

    fun `test that we verify a signature given expected input when we fail`() {
        // Given
        every { mockResources.getString(R.string.no_signature_match, any()) } answers {
            args[1].toStr()
        }

        // When
        val reportResult = SignatureVerificationCheck(listOf("abc"), sdkVersion)
            .canRun(mockContext) as SafeToRunReport.SafeToRunReportFailure

        // Then
        assertThat(reportResult.failureMessage).contains("WgtvIJ0A//RKGHShVDQRPdd/9ks=")
    }

    fun `test that we verify a signature given expected input`() {
        // Given
        val signatureMatch = "Signature match"
        every { mockResources.getString(R.string.signature_match) } returns signatureMatch

        // When
        val reportResult = SignatureVerificationCheck(listOf("WgtvIJ0A//RKGHShVDQRPdd/9ks="), sdkVersion)
            .canRun(mockContext) as SafeToRunReport.SafeToRunReportSuccess

        // Then
        assertThat(reportResult.successMessage).isEqualTo(signatureMatch)
    }

    fun `test that we fail a signature if it is not possible to generate`() {
        // Given
        val signatureMatch = "No signature match"
        every { mockResources.getString(R.string.signature_not_found) } returns signatureMatch
        every { signature.toByteArray() } returns null

        // When
        val reportResult = SignatureVerificationCheck(listOf("WgtvIJ0A//RKGHShVDQRPdd/9ks="), sdkVersion)
            .canRun(mockContext) as SafeToRunReport.SafeToRunReportFailure

        // Then
        assertThat(reportResult.failureMessage).isEqualTo(signatureMatch)
    }

    fun `test that we verify a signature given expected input when we fail_lower_sdk`() {
        // Given
        every { mockResources.getString(R.string.no_signature_match, any()) } answers {
            args[1].toStr()
        }

        // When
        val reportResult = SignatureVerificationCheck(listOf("abc"), 27)
            .canRun(mockContext) as SafeToRunReport.SafeToRunReportFailure

        // Then
        assertThat(reportResult.failureMessage).contains("WgtvIJ0A//RKGHShVDQRPdd/9ks=")
    }

    fun `test that we verify a signature given expected input_lower_sdk`() {
        // Given
        val signatureMatch = "Signature match"
        every { mockResources.getString(R.string.signature_match) } returns signatureMatch

        // When
        val reportResult = SignatureVerificationCheck(listOf("WgtvIJ0A//RKGHShVDQRPdd/9ks="), 27)
            .canRun(mockContext) as SafeToRunReport.SafeToRunReportSuccess

        // Then
        assertThat(reportResult.successMessage).isEqualTo(signatureMatch)
    }

    fun `test that we fail a signature if it is not possible to generate_lower_sdk`() {
        // Given
        val signatureMatch = "No signature match"
        every { mockResources.getString(R.string.signature_not_found) } returns signatureMatch
        every { signature.toByteArray() } returns null

        // When
        val reportResult = SignatureVerificationCheck(listOf("WgtvIJ0A//RKGHShVDQRPdd/9ks="), 27)
            .canRun(mockContext) as SafeToRunReport.SafeToRunReportFailure

        // Then
        assertThat(reportResult.failureMessage).isEqualTo(signatureMatch)
    }

    companion object {
        const val OUR_PACKAGE = "com.abc.def"
    }
}