package io.github.dllewellyn.safetorun.offdevice

import android.content.Context
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import junit.framework.TestCase

internal class AndroidSafeToRunOffDeviceKtTest : TestCase() {

    private val context = mockk<Context>()

    fun `test device returns as expected`() {
        val safeToRun = context.safeToRunOffDevice("Url", "ApiKey")
        assertThat(safeToRun).isNotNull()
    }
}
