package com.safetorun.inputverification.dto

import com.safetorun.inputverification.model.AllowedTypeCore
import com.safetorun.inputverification.model.ParameterConfig
import com.safetorun.inputverification.model.UrlConfiguration
import com.safetorun.inputverification.model.UrlConfigurations
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
/**
 * URL configuration
 */
data class UrlConfigurationsDto(val name: String, val configuration: UrlConfigurationDto)

@Serializable
/**
 * URL configuration
 */
data class UrlConfigurationDto(
    @SerialName("allowed_hosts") val allowedHost: List<String>,
    @SerialName("allowed_urls") val allowedUrls: List<String>,
    @SerialName("allowed_parameters") val allowParameters: List<ParameterConfigDto>,
    @SerialName("allow_any_parameter") val allowAnyParameter: Boolean,
    @SerialName("allow_any_url") val allowAnyUrl: Boolean
)

@Serializable
/**
 * Parameter config
 */
data class ParameterConfigDto(
    @SerialName("parameter_name") val parameterName: String,
    @SerialName("allowed_type") val allowedType: AllowedTypeDto
)

@Serializable
/**
 * Allowed type
 */
enum class AllowedTypeDto {
    /**
     * Any type allowed
     */
    Any,

    /**
     * Only string allowed
     */
    String,

    /**
     * Only boolean allowed
     */
    Boolean,

    /**
     * Only int allowed
     */
    Int,

    /**
     * Only double allowed
     */
    Double
}

/**
 * Convert URL configuration DTO to core version
 */
fun UrlConfigurationsDto.toCore() = UrlConfigurations(
    name,
    UrlConfiguration(
        allowedHost = configuration.allowedHost,
        allowedUrls = configuration.allowedUrls,
        allowAnyParameter = configuration.allowAnyParameter,
        allowAnyUrl = configuration.allowAnyUrl,
        allowParameters = configuration.allowParameters.toCore()
    )
)

private fun List<ParameterConfigDto>.toCore() =
    map {
        ParameterConfig(
            it.parameterName, when (it.allowedType) {
                AllowedTypeDto.Any -> AllowedTypeCore.Any
                AllowedTypeDto.String -> AllowedTypeCore.String
                AllowedTypeDto.Boolean -> AllowedTypeCore.Boolean
                AllowedTypeDto.Int -> AllowedTypeCore.Int
                AllowedTypeDto.Double -> AllowedTypeCore.Double
            }
        )
    }

/**
 * Convert params to dtos
 */
internal fun List<ParameterConfig>.toDto() =
    map {
        ParameterConfigDto(
            it.parameterName, when (it.allowedType) {
                AllowedTypeCore.Any -> AllowedTypeDto.Any
                AllowedTypeCore.String -> AllowedTypeDto.String
                AllowedTypeCore.Boolean -> AllowedTypeDto.Boolean
                AllowedTypeCore.Int -> AllowedTypeDto.Int
                AllowedTypeCore.Double -> AllowedTypeDto.Double
            }
        )
    }
