package com.safetorun.inputverification.builders

import com.google.common.truth.Truth
import com.safetorun.inputverification.configurationParser
import com.safetorun.inputverification.dto.AllowedTypeDto
import com.safetorun.inputverification.dto.ParameterConfigDto
import com.safetorun.inputverification.dto.ParentConfigurationDto
import com.safetorun.inputverification.model.AllowedTypeCore
import com.safetorun.inputverification.model.ParameterConfig
import org.junit.Test
import java.io.File

internal class SafeToRunInputVerificationParserTest {

    @Test
    fun `test that safe to run verification can be parsed from file`() {
        this::class.java.classLoader.getResource("safe_to_run_input_verification.json")?.let {
            configurationParser(File(it.file)).inputVerification
                ?.let {
                    Truth.assertThat(it.fileConfiguration.first().name).isEqualTo("file_verifier")
                    Truth.assertThat(it.fileConfiguration.first().configuration.allowAnyFile)
                        .isFalse()
                    Truth.assertThat(it.fileConfiguration.first().configuration.allowedExactFiles)
                        .isEqualTo(listOf("/data/data/com.blah/abc"))
                    Truth.assertThat(it.fileConfiguration.first().configuration.allowedParentDirectories)
                        .isEqualTo(
                            listOf(ParentConfigurationDto("/data/data/com.blah/parent", false))
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
                                ParameterConfigDto(
                                    "blah",
                                    AllowedTypeDto.String
                                )
                            )
                        )
                }
        } ?: throw NullPointerException("")
    }
}
