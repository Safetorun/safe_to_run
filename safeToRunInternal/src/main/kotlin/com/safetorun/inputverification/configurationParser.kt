package com.safetorun.inputverification

import com.safetorun.inputverification.builders.UrlConfigurationBuilder
import com.safetorun.inputverification.dto.FileConfigurationsDto
import com.safetorun.inputverification.dto.SafeToRunInputVerificationDto
import com.safetorun.inputverification.dto.UrlConfigurationsDto
import com.safetorun.inputverification.dto.toCore
import com.safetorun.inputverification.model.SafeToRunInputVerification
import kotlinx.serialization.json.Json
import java.net.URL

/**
 * Function to configure files
 *
 * @param fileName configuration file url
 */
fun configurationParser(fileName: URL) =
    Json.decodeFromString(
        SafeToRunInputVerificationDto.serializer(),
        fileName.readText(),
    ).let {
        SafeToRunInputVerification(
            fileConfiguration = it.fileConfiguration.map { conf -> conf.toCore() },
            urlConfigurations = it.urlConfigurations.map { conf -> conf.toCore() }
        )
    }


/**
 * Input verfication builder
 */
class InputVerificationBuilder internal constructor() {

    private val urlConfigurations = mutableListOf<UrlConfigurationsDto>()
    private val fileConfiguration = mutableListOf<FileConfigurationsDto>()

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
