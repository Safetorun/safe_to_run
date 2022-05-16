package com.safetorun.intents

import android.content.Context
import com.google.common.truth.Truth.assertThat
import com.safetorun.intents.configurator.initialiseSafeToRunConfigurator
import com.safetorun.intents.configurator.registerUrlVerification
import com.safetorun.intents.configurator.verifyUrl
import com.safetorun.intents.exeptions.ConfigurationNotFoundException
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SafeToRunUrlConfigurationTest {
    private val context = mockk<Context>()

    @Before
    fun beforeAll() {
        initialiseSafeToRunConfigurator(context)
    }

    @Test
    fun `test that url verification will throw an exception if the configuration doesnt work`() {
        assertThrows<ConfigurationNotFoundException> {
            "".verifyUrl("notexists")
        }
    }

    @Test
    fun `test that url verification passes if the validation fails`() {
        val verifierName = "verifier"
        registerUrlVerification(verifierName) {

        }

        val result = "https://safetorun.com".verifyUrl(verifierName)
        assertThat(result).isFalse()
    }

    @Test
    fun `test that url verification passes if the validation succeeds`() {
        val verifierName = "verifier"
        registerUrlVerification(verifierName) {
            "safetorun.com".allowHost()
        }

        val result = "https://safetorun.com".verifyUrl(verifierName)
        assertThat(result).isTrue()
    }


    @Test
    fun `test that url verification calls callback if the validation fails`() {
        val verifierName = "verifier"
        var called = false
        registerUrlVerification(verifierName) {
            "safetorun.com".allowHost()
        }

        "https://safetorun.com".verifyUrl(verifierName) {
            called = true
        }
        assertThat(called).isFalse()
    }

    @Test
    fun `test that url verification calls callback if the validation succeeds`() {
        val verifierName = "verifier"
        var called = false
        registerUrlVerification(verifierName) {
        }

        "https://safetorun.com".verifyUrl(verifierName) {
            called = true
        }
        assertThat(called).isTrue()
    }
}
