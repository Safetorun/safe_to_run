package com.safetorun.inputverification.builders

import com.google.common.truth.Truth.assertThat
import kotlinx.serialization.json.Json
import org.junit.Test

class FileConfigurationDtoTest {

    @Test
    fun `test that we can file configuration from disk`() {
        this::class.java.classLoader.getResource("file_configuration_sample.json")?.let {
            val response = Json.decodeFromString(
                FileConfigurationsDto.serializer(),
                it.readText(),
            )

            assertThat(response.name).isEqualTo("file_verifier")
            assertThat(response.configuration.allowAnyFile).isFalse()
            assertThat(response.configuration.allowedExactFiles).isEqualTo(listOf("/data/data/com.blah/abc"))
            assertThat(response.configuration.allowedParentDirectories).isEqualTo(
                listOf(ParentConfigurationDto("/data/data/com.blah/parent", false))
            )

        } ?: throw NullPointerException("file configuration not loaded")
    }
}
