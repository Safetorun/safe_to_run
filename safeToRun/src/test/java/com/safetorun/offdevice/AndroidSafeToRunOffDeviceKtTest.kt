package com.safetorun.offdevice

import android.content.Context
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class AndroidSafeToRunOffDeviceKtTest : TestCase() {

    private val context: Context by lazy {
        mockk<Context>().apply {
            every { getSharedPreferences(any(), any()) } returns FakeSharedPrefs()
        }
    }

    fun `test device returns as expected and returns the same result if given correct`() {
        val safeToRun = context.safeToRunOffDevice("Url", "ApiKey")
        assertThat(safeToRun).isNotNull()
        assertThat(safeToRun).isEqualTo(context.safeToRunOffDevice("Url", "ApiKey"))
        assertThat(safeToRun).isNotEqualTo(context.safeToRunOffDevice("DifferentUrl", "DifferentApiKey"))
    }
}
