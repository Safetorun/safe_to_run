package com.safetorun

import androidx.test.platform.app.InstrumentationRegistry
import com.safetorun.features.oscheck.banAvdEmulator
import com.safetorun.features.oscheck.banBluestacksEmulator
import com.safetorun.features.oscheck.banGenymotionEmulator
import com.safetorun.features.oscheck.osDetectionCheck
import com.safetorun.reporting.anyFailures
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
