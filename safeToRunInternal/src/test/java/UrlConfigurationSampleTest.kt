package com.safetorun.inputverification.builders

import com.google.common.truth.Truth.assertThat
import com.safetorun.inputverification.builders.dto.AllowedTypeDto
import com.safetorun.inputverification.builders.dto.ParameterConfigDto
import com.safetorun.inputverification.builders.dto.UrlConfigurationsDto
import kotlinx.serialization.json.Json.Default.decodeFromString
import org.junit.Test
import java.lang.NullPointerException

internal class UrlConfigurationSampleTest {

    @Test
    fun `test that url configuration builds correctly`() {

        this::class.java.classLoader.getResource("url_configuration_sample.json")?.let {
            val response = decodeFromString(
                UrlConfigurationsDto.serializer(),
                it.readText(),
            )

            assertThat(response.name).isEqualTo("example_configuration")

            val configuration = response.configuration

            assertThat(configuration.allowAnyUrl).isFalse()
            assertThat(configuration.allowAnyParameter).isFalse()
            assertThat(configuration.allowedUrls).isEqualTo(listOf("https://safetorun.com?abc=def"))
            assertThat(configuration.allowedHost).isEqualTo(listOf("safetorun.com"))
            assertThat(configuration.allowParameters).isEqualTo(
                listOf(
                    ParameterConfigDto(
                        "blah",
                        AllowedTypeDto.String
                    )
                )
            )


        } ?: throw NullPointerException("Something went wrong reading json file")
    }
}
