package com.safetorun.intents.configuration

import android.content.Context
import com.google.common.truth.Truth.assertThat
import com.safetorun.inputverification.builders.AllowedTypeDto
import com.safetorun.inputverification.builders.FileConfigurationDto
import com.safetorun.inputverification.builders.FileConfigurationsDto
import com.safetorun.inputverification.builders.ParameterConfigDto
import com.safetorun.inputverification.builders.ParentConfigurationDto
import com.safetorun.inputverification.builders.UrlConfigurationDto
import com.safetorun.inputverification.builders.UrlConfigurationsDto
import com.safetorun.intents.configurator.initialiseSafeToRunConfigurator
import com.safetorun.intents.configurator.register
import com.safetorun.intents.configurator.verifyIntent
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
        UrlConfigurationsDto(
            verifierName,
            UrlConfigurationDto(
                listOf("safetorun.com"),
                listOf("https://safetorun.com?abc=def"),
                listOf(ParameterConfigDto("def", AllowedTypeDto.String)),
                allowAnyParameter = false,
                allowAnyUrl = false
            )
        ).register()

        assertThat("https://safetorun.com".verifyIntent(verifierName)).isTrue()
        assertThat("https://safetorun.com?blah=def".verifyIntent(verifierName)).isFalse()
        assertThat("https://safetorun.com?abc=def".verifyIntent(verifierName)).isTrue()
        assertThat("https://safetorun.com?def=abc".verifyIntent(verifierName)).isTrue()
    }

    @Test
    fun `test that we can register a file handler from dto`() {
        val verifierName = "test_name"
        FileConfigurationsDto(
            verifierName,
            FileConfigurationDto(
                false,
                listOf("/data/data/${DefaultFileUriMatcherBuilderTest.PACKAGE}/def"),
                listOf(
                    ParentConfigurationDto(
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
