package io.github.dllewellyn.safetorun.offdevice

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class AndroidSafeToRunOffDeviceKtTest : TestCase() {

    private val context: Context by lazy { ApplicationProvider.getApplicationContext() }

    @Test
    fun `test device returns as expected and returns the same result if given correct`() {
        val safeToRun = context.safeToRunOffDevice("Url", "ApiKey")
        assertThat(safeToRun).isNotNull()
        assertThat(safeToRun).isEqualTo(context.safeToRunOffDevice("Url", "ApiKey"))
        assertThat(safeToRun).isNotEqualTo(context.safeToRunOffDevice("DifferentUrl", "DifferentApiKey"))
    }
}
