package com.safetorun.intents

import android.content.Context
import android.content.Intent
import com.google.common.truth.Truth.assertThat
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
internal class SafeToRunIntentConfiguratorTest {

    private val context = mockk<Context>().apply {
        every { packageName } returns DefaultFileUriMatcherBuilderTest.PACKAGE
    }

    @Before
    fun beforeAll() {
        initialiseSafeToRunIntentConfigurator(context)
    }

    @Test
    fun `test that safe to configuration throws exception if it doesn't exist`() {
        assertThrows<ConfigurationNotFoundException> {
            verifyIntent("notexist", Intent())
        }
    }

    @Test
    fun `test that safe to configuration fails if intent fails`() {
        val verifierName = "exists"
        registerIntentVerification(verifierName) {
            // Do nothing - URL should not be allowed in this set up
        }

        assertThat(verifyIntent(verifierName,
            Intent().putExtra("url", "https://safetorun.com")))
            .isFalse()

    }
}
