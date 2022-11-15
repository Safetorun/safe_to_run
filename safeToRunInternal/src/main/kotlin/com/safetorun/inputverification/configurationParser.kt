package com.safetorun.inputverification

import com.safetorun.SafeToRunConfiguration
import com.safetorun.inputverification.builders.FileConfigurationBuilder
import com.safetorun.inputverification.builders.UrlConfigurationBuilder
import com.safetorun.inputverification.dto.FileConfigurationsDto
import com.safetorun.inputverification.dto.SafeToRunInputVerificationDto
import com.safetorun.inputverification.dto.UrlConfigurationsDto
import kotlinx.serialization.json.Json
import java.io.File

/**
 * Function to configure files
 *
 * @param fileName configuration file url
 */
fun configurationParser(fileName: File) =
    Json.decodeFromString(
        SafeToRunConfiguration.serializer(),
        fileName.readText(),
    )


/**
 * Input verfication builder
 */
class InputVerificationBuilder internal constructor() {

    private val urlConfigurations = mutableListOf<UrlConfigurationsDto>()
    private val fileConfiguration = mutableListOf<FileConfigurationsDto>()

    /**
     * Add a file configuration
     *
     * @param name the name of the file configuration
     * @param configuration the configuration to add
     */
    fun fileConfiguration(name: String, configuration: FileConfigurationBuilder.() -> Unit) {
        fileConfiguration.add(
            FileConfigurationsDto(
                name,
                FileConfigurationBuilder().apply(configuration).build()
            )
        )
    }

    /**
     * Add a URL configuration
     *
     * @param name name of configuration
     * @param configuration url configuration
     */
    fun urlConfiguration(name: String, configuration: UrlConfigurationBuilder.() -> Unit) =
        urlConfigurations.add(UrlConfigurationBuilder(name).apply(configuration).build())

    internal fun build() = SafeToRunInputVerificationDto(
        urlConfigurations = urlConfigurations,
        fileConfiguration = fileConfiguration
    )
}
