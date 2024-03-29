package com.safetorun.features.signatureverify

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.content.pm.SigningInfo
import android.util.Base64
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase

@Suppress("DEPRECATION")
internal class VerifySignatureConfigKtTest : TestCase() {

    private val mockContext = mockk<Context>()
    private val mockPackageManager = mockk<PackageManager>()
    private val mockPackage = mockk<PackageInfo>()
    private val signatureInfo = mockk<SigningInfo>()
    private val signature = mockk<Signature>()

    private val sdkVersions = listOf(21, 30)

    override fun setUp() {
        every { mockContext.packageManager } returns mockPackageManager
        every { mockContext.packageName } returns OUR_PACKAGE
        every {
            mockPackageManager.getPackageInfo(
                OUR_PACKAGE,
                any<PackageManager.PackageInfoFlags>()
            )
        } returns mockPackage

        every {
            mockPackageManager.getPackageInfo(
                OUR_PACKAGE,
                any<Int>()
            )
        } returns mockPackage

        mockPackage.signingInfo = signatureInfo
        mockPackage.signatures = arrayOf(signature)
        every { signatureInfo.apkContentsSigners } returns arrayOf(signature)
        every { signature.toByteArray() } returns EXPECTED_SIGNATURE.toByteArray()
        mockkStatic(Base64::class)
        every { Base64.encodeToString(any(), any()) } answers {
            EXPECTED_SIGNATURE
        }
    }

    fun `test builder function dont return  null`() {
        assertThat(mockContext.verifySignatureCheck()).isNotNull()
    }

    fun `test that verifySignatureCheck works as expected if signature matches`() {
        sdkVersions.forEach {
            assertThat(mockContext.verifySignatureCheckOverrideVersion(it, EXPECTED_SIGNATURE)).isFalse()
        }
    }

    fun `test that verifySignatureCheck works as expected if signature does not match`() {
        sdkVersions.forEach {
            assertThat(mockContext.verifySignatureCheckOverrideVersion(it, "Something else")).isTrue()
        }
    }

    fun `test that verifySignatureCheck works as expected if signature is not found`() {
        mockPackage.signatures = emptyArray()
        every { signatureInfo.apkContentsSigners } returns emptyArray()

        sdkVersions.forEach {
            assertThat(mockContext.verifySignatureCheckOverrideVersion(it, EXPECTED_SIGNATURE)).isTrue()
        }
    }

    companion object {
        private const val OUR_PACKAGE = "com.abc.def"
        private const val EXPECTED_SIGNATURE = "fake signature"
    }
}
