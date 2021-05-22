package io.github.dllewellyn.safetorun.offdevice

import android.content.Context
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import junit.framework.TestCase

internal class AndroidSafeToRunOffDeviceKtTest : TestCase() {

    private val context = mockk<Context>()

    fun `test device returns as expected and returns the same result if given correct`() {
        val safeToRun = context.safeToRunOffDevice("Url", "ApiKey")
        assertThat(safeToRun).isNotNull()
        assertThat(safeToRun).isEqualTo(context.safeToRunOffDevice("Url", "ApiKey"))
        assertThat(safeToRun).isNotEqualTo(context.safeToRunOffDevice("DifferentUrl", "DifferentApiKey"))
    }
}
