package com.safetorun.intents

import android.content.Context
import android.content.Intent
import com.google.common.truth.Truth.assertThat
import com.safetorun.intents.configurator.initialiseSafeToRunConfigurator
import com.safetorun.intents.configurator.registerIntentVerification
import com.safetorun.intents.configurator.verifyUrl
import com.safetorun.intents.configurator.verifyIntent
import com.safetorun.intents.exeptions.ConfigurationNotFoundException
import com.safetorun.intents.file.DefaultFileUriMatcherBuilderTest
import com.safetorun.intents.url.urlConfiguration
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
        initialiseSafeToRunConfigurator(context)
    }

    @Test
    fun `test that safe to configuration throws exception if it doesn't exist`() {
        assertThrows<ConfigurationNotFoundException> {
            Intent().verifyIntent("notexist")
        }
    }

    @Test
    fun `test that safe to configuration passes if intent passes`() {
        val verifierName = "exists"
        registerIntentVerification(verifierName) {
            +urlConfiguration { "safetorun.com".allowHost() }
        }

        val passed = Intent().putExtra("url", SAFETORUN)
            .verifyIntent(verifierName)

        assertThat(passed).isTrue()
    }

    @Test
    fun `test that safe to configuration passes if intent does not call extra function`() {
        val verifierName = "exists"
        var called = false
        registerIntentVerification(verifierName) {
            urlConfiguration { "safetorun.com".allowHost() }
        }

        Intent().putExtra("url", SAFETORUN).verifyUrl(verifierName) {
            called = true
        }
        assertThat(called).isFalse()
    }


    @Test
    fun `test that safe to configuration fails if intent fails extra called`() {
        val verifierName = "exists"
        var called = false
        registerIntentVerification(verifierName) {
            // Do nothing - URL should not be allowed in this set up
        }

        Intent().putExtra("url", SAFETORUN).verifyUrl(verifierName) {
            called = true
        }

        assertThat(called).isTrue()
    }

    @Test
    fun `test that safe to configuration fails if intent fails`() {
        val verifierName = "exists"
        registerIntentVerification(verifierName) {
            // Do nothing - URL should not be allowed in this set up
        }
        val passed = Intent().putExtra("url", SAFETORUN).verifyIntent(
            verifierName
        )
        assertThat(passed).isFalse()
    }

    companion object {
        const val SAFETORUN = "https://safetorun.com"
    }
}
