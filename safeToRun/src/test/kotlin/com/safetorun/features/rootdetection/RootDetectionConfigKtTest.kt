package com.safetorun.features.rootdetection

import android.content.Context
import android.util.Log
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase

internal class RootDetectionConfigKtTest : TestCase() {


    fun `test that root detection does not return null`() {
        val context = mockk<Context>(relaxed = true)
        mockkStatic(Log::class)
        every { Log.d(any(), any()) }
        every { Log.e(any(), any()) } answers { 0 }

        val result = context.rootDetectionCheck(false)
        assertThat(result).isNotNull()
    }
}
