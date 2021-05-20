package io.github.dllewellyn.safetorun

import androidx.test.platform.app.InstrumentationRegistry
import io.github.dllewellyn.safetorun.api.DefaultHttpClient
import io.github.dllewellyn.safetorun.api.DefaultSafeToRunApi
import io.github.dllewellyn.safetorun.models.models.DeviceSignatureDto
import io.github.dllewellyn.safetorun.offdevice.safeToRunOffDevice
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test

class SafeToRunOffDeviceTest {

    @Test
    fun testThatWeCanDoAnOffDeviceTest() {
        val result =
            with(
                InstrumentationRegistry.getInstrumentation().context.safeToRunOffDevice(
                    "https://rygl69bpz0.execute-api.eu-west-1.amazonaws.com/Prod",
                    "5bzdwZ8Drs1AIsmJAx0M37bndOeEkwbv6pI5fjx1"
                )
            ) {
                isSafeToRun().let {
                    DefaultSafeToRunApi(
                        DefaultHttpClient("https://rygl69bpz0.execute-api.eu-west-1.amazonaws.com/Prod"),
                        "5bzdwZ8Drs1AIsmJAx0M37bndOeEkwbv6pI5fjx1",
                    ).verifyDataResult(DeviceSignatureDto(it.signedResult))
                }
            }

        assertTrue(result.correctIssuer)
        assertFalse(result.expired)
        assertFalse(result.anyFailures)
        assertTrue(result.correctSignature)
    }
}
