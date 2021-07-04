package io.github.dllewellyn.safetorun

import androidx.test.platform.app.InstrumentationRegistry
import io.github.dllewellyn.safetorun.features.oscheck.banAvdEmulator
import io.github.dllewellyn.safetorun.features.oscheck.banBluestacksEmulator
import io.github.dllewellyn.safetorun.features.oscheck.banGenymotionEmulator
import io.github.dllewellyn.safetorun.features.oscheck.osDetectionCheck
import io.github.dllewellyn.safetorun.reporting.anyFailures
import junit.framework.TestCase.assertTrue
import org.junit.Test

internal class SafeToRunEmulatorCheckTest {

    @Test
    fun testThatRunningDeviceWillFailTheEmulatorChecks() {

        with(InstrumentationRegistry.getInstrumentation().context) {
            SafeToRun.init {
                configure {
                    osDetectionCheck(banAvdEmulator()).error()
                    osDetectionCheck(banBluestacksEmulator()).error()
                    osDetectionCheck(banGenymotionEmulator()).error()
                }
            }

            assertTrue(SafeToRun.isSafeToRun().anyFailures())
        }
    }
}
