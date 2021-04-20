package com.andro.safetorun.features.signatureverify

import android.content.Context
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import junit.framework.TestCase

internal class VerifySignatureConfigKtTest : TestCase() {

    private val mockContext = mockk<Context>()

    fun `test builder function dont return  null`() {
        assertThat(mockContext.verifySignatureConfig()).isNotNull()
        assertThat(mockContext.verifySignatureConfigOverrideSdkVersion(1)).isNotNull()
    }

}