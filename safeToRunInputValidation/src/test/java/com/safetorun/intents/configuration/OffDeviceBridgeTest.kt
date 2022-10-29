package com.safetorun.intents.configuration

import android.content.Context
import com.google.common.truth.Truth.assertThat
import com.safetorun.inputverification.model.AllowedTypeCore
import com.safetorun.inputverification.model.FileConfiguration
import com.safetorun.inputverification.model.FileConfigurations
import com.safetorun.inputverification.model.ParameterConfig
import com.safetorun.inputverification.model.ParentConfiguration
import com.safetorun.inputverification.model.UrlConfiguration
import com.safetorun.inputverification.model.UrlConfigurations
import com.safetorun.intents.configurator.initialiseSafeToRunConfigurator
import com.safetorun.intents.configurator.register
import com.safetorun.intents.configurator.verifyUrl
import com.safetorun.intents.configurator.verifyFile
import com.safetorun.intents.file.DefaultFileUriMatcherBuilderTest
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.File

@RunWith(RobolectricTestRunner::class)
class OffDeviceBridgeTest {

    private val context = mockk<Context>().apply {
        every { packageName } returns DefaultFileUriMatcherBuilderTest.PACKAGE
    }

    @Before
    fun before() {
        initialiseSafeToRunConfigurator(context)
    }

    @Test
    fun `test that off device test can load configurations from a dto`() {
        val verifierName = "test_name"
        UrlConfigurations(
            verifierName,
            UrlConfiguration(
                listOf("safetorun.com"),
                listOf("https://safetorun.com?abc=def"),
                listOf(ParameterConfig("def", AllowedTypeCore.String)),
                allowAnyParameter = false,
                allowAnyUrl = false
            )
        ).register()

        assertThat("https://safetorun.com".verifyUrl(verifierName)).isTrue()
        assertThat("https://safetorun.com?blah=def".verifyUrl(verifierName)).isFalse()
        assertThat("https://safetorun.com?abc=def".verifyUrl(verifierName)).isTrue()
        assertThat("https://safetorun.com?def=abc".verifyUrl(verifierName)).isTrue()
    }

    @Test
    fun `test that we can register a file handler from dto`() {
        val verifierName = "test_name"
        FileConfigurations(
            verifierName,
            FileConfiguration(
                false,
                listOf("/data/data/${DefaultFileUriMatcherBuilderTest.PACKAGE}/def"),
                listOf(
                    ParentConfiguration(
                        "/data/data/${DefaultFileUriMatcherBuilderTest.PACKAGE}/parent",
                        false
                    )
                )
            )
        ).register()

        assertThat(
            File("/data/data/${DefaultFileUriMatcherBuilderTest.PACKAGE}/abc").verifyFile(
                verifierName
            )
        ).isFalse()

        assertThat(
            File("/data/data/${DefaultFileUriMatcherBuilderTest.PACKAGE}/parent/abc").verifyFile(
                verifierName
            )
        ).isTrue()

        assertThat(
            File("/data/data/${DefaultFileUriMatcherBuilderTest.PACKAGE}/def").verifyFile(
                verifierName
            )
        ).isTrue()

    }
}
