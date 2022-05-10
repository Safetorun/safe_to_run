package io.github.dllewellyn.safetorun

import androidx.test.platform.app.InstrumentationRegistry
import io.github.dllewellyn.safetorun.api.DefaultHttpClient
import io.github.dllewellyn.safetorun.api.DefaultSafeToRunApi
import com.safetorun.models.models.DeviceSignatureDto
import io.github.dllewellyn.safetorun.offdevice.safeToRunOffDevice
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test

internal class SafeToRunOffDeviceTest {

    companion object {
        const val URL = "https://qm37a5nppe.execute-api.eu-west-1.amazonaws.com/Prod/"
        const val API_KEY = "cGYypq1epDguXVB6qDvJ5noPCZyMv9W4wB6t64Gf"
    }

    @Test
    fun testThatWeCanDoAnOffDeviceTest() {
        val result =
            with(
                InstrumentationRegistry.getInstrumentation().context.safeToRunOffDevice(
                    URL,
                    API_KEY
                )
            ) {
                isSafeToRun().let {
                    DefaultSafeToRunApi(
                        DefaultHttpClient(URL),
                        API_KEY,
                    ).verifyDataResult(DeviceSignatureDto(it.signedResult))
                }
            }

        assertTrue(result.correctIssuer)
        assertFalse(result.expired)
        assertFalse(result.anyFailures)
        assertTrue(result.correctSignature)
    }
}
