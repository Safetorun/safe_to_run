package com.safetorun.inputverification.builders

import com.google.common.truth.Truth
import com.safetorun.inputverification.configurationParser
import com.safetorun.inputverification.model.AllowedTypeCore
import com.safetorun.inputverification.model.ParameterConfig
import com.safetorun.inputverification.model.ParentConfiguration
import org.junit.Test

internal class SafeToRunInputVerificationParserTest {

    @Test
    fun `test that safe to run verification can be parsed from file`() {
        this::class.java.classLoader.getResource("safe_to_run_input_verification.json")?.let {
            configurationParser(it)
                .let {
                    Truth.assertThat(it.fileConfiguration.first().name).isEqualTo("file_verifier")
                    Truth.assertThat(it.fileConfiguration.first().configuration.allowAnyFile)
                        .isFalse()
                    Truth.assertThat(it.fileConfiguration.first().configuration.allowedExactFiles)
                        .isEqualTo(listOf("/data/data/com.blah/abc"))
                    Truth.assertThat(it.fileConfiguration.first().configuration.allowedParentDirectories)
                        .isEqualTo(
                            listOf(ParentConfiguration("/data/data/com.blah/parent", false))
                        )

                    Truth.assertThat(it.urlConfigurations.first().configuration.allowAnyUrl)
                        .isFalse()
                    Truth.assertThat(it.urlConfigurations.first().configuration.allowAnyParameter)
                        .isFalse()
                    Truth.assertThat(it.urlConfigurations.first().configuration.allowedUrls)
                        .isEqualTo(listOf("https://safetorun.com?abc=def"))
                    Truth.assertThat(it.urlConfigurations.first().configuration.allowedHost)
                        .isEqualTo(listOf("safetorun.com"))
                    Truth.assertThat(it.urlConfigurations.first().configuration.allowParameters)
                        .isEqualTo(
                            listOf(
                                ParameterConfig(
                                    "blah",
                                    AllowedTypeCore.String
                                )
                            )
                        )
                }
        } ?: throw NullPointerException("")
    }
}
