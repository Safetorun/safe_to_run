package com.safetorun.intents

import android.content.Context
import android.net.Uri
import com.google.common.truth.Truth.assertThat
import com.safetorun.intents.configurator.initialiseSafeToRunConfigurator
import com.safetorun.intents.configurator.registerFileVerification
import com.safetorun.intents.configurator.verifyFile
import com.safetorun.intents.exeptions.ConfigurationNotFoundException
import com.safetorun.intents.file.DefaultFileUriMatcherBuilderTest
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SafeToRunFileUriConfiguratorTest {

    private val context = mockk<Context>().apply {
        every { packageName } returns DefaultFileUriMatcherBuilderTest.PACKAGE
    }

    @Before
    fun before() {
        initialiseSafeToRunConfigurator(context)
    }

    @Test
    fun `test that when a check is not configured it throws an exception`() {
        val notExists = "notexists"
        assertThrows<ConfigurationNotFoundException> {
            Uri.parse("/sdcard/abc").verifyFile(notExists)
        }
    }

    @Test
    fun `test that when a check is configured and should pass it passes`() {
        val verificationConfig = "fileverifier"
        registerFileVerification(verificationConfig) {
            allowAnyFile = true
        }

        assertThat(
            privateDirectoryFolder().verifyFile(verificationConfig)
        ).isTrue()
    }

    @Test
    fun `test that when a check is configured and should fail it fails`() {
        val verificationConfig = "fileverifier"
        registerFileVerification(verificationConfig) {
        }

        assertThat(
            privateDirectoryFolder().verifyFile(verificationConfig)
        ).isFalse()
    }

    @Test
    fun `test that when a check is configured and should not call failure state`() {
        var called = false
        val verificationConfig = "fileverifier"
        registerFileVerification(verificationConfig) {
            allowAnyFile = true
        }

        privateDirectoryFolder().verifyFile(verificationConfig) {
            called = true
        }
        assertThat(called).isFalse()
    }

    @Test
    fun `test that when a check is configured and should fail call failure state`() {
        val verificationConfig = "fileverifier"
        var called = false
        registerFileVerification(verificationConfig) {
        }

        privateDirectoryFolder().verifyFile(verificationConfig) {
            called = true
        }
        assertThat(called).isTrue()
    }

    private fun privateDirectoryFolder() =
        Uri.parse("/data/data/${DefaultFileUriMatcherBuilderTest.PACKAGE}/blah")
}
