package com.andro.safetorun.features.signatureverify

import android.content.Context
import android.content.res.Resources
import com.andro.safetorun.R
import com.andro.safetorun.features.oscheck.AndroidOSDetectionStringsTest
import com.google.common.truth.Truth.assertThat
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class AndroidSignatureVerificationStringsImplTest : TestCase() {

    private val mockContext = mockk<Context>()
    private val mockResources = mockk<Resources>()

    override fun setUp() {
        every { mockContext.resources } returns mockResources
        every { mockResources.getString(R.string.signature_match) } returns SUCCESS_MESSAGE
        every { mockResources.getString(R.string.no_signature_match, any()) } answers {
            FAILURE_MESSAGE.format(args[1].toStr().removePrefix("[").removeSuffix("]"))
        }

        every { mockResources.getString(R.string.signature_not_found) } returns NOT_FOUND_MESSAGE
    }

    fun `test that strings return expected`() {
        with (AndroidSignatureVerificationStringsImpl(mockContext)) {
            assertThat(signatureMatchesMessage()).isEqualTo(SUCCESS_MESSAGE)
            assertThat(signatureNotFoundMessage()).isEqualTo(NOT_FOUND_MESSAGE)
            assertThat(signatureNotMatchedMessage("Abc")).isEqualTo(FAILURE_MESSAGE.format("Abc"))
        }
    }

    companion object {
        const val SUCCESS_MESSAGE = "Success"
        const val FAILURE_MESSAGE = "Failure message %s"
        const val NOT_FOUND_MESSAGE = "Not found "
    }
}