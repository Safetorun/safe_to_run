package com.safetorun.inputverification.builders.dto

import com.safetorun.inputverification.builders.model.AllowedTypeCore
import com.safetorun.inputverification.builders.model.ParameterConfig
import com.safetorun.inputverification.builders.model.UrlConfiguration
import com.safetorun.inputverification.builders.model.UrlConfigurations
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
/**
 * URL configuration
 */
internal data class UrlConfigurationsDto(val name: String, val configuration: UrlConfigurationDto)

@Serializable
/**
 * URL configuration
 */
internal data class UrlConfigurationDto(
    @SerialName("allowed_hosts") val allowedHost: List<String>,
    @SerialName("allowed_urls") val allowedUrls: List<String>,
    @SerialName("allowed_parameters") val allowParameters: List<ParameterConfigDto>,
    @SerialName("allow_any_parameter") val allowAnyParameter: Boolean,
    @SerialName("allow_any_url") val allowAnyUrl: Boolean
) {
    companion object {
        fun empty() = UrlConfigurationDto(emptyList(), emptyList(), emptyList(), false, false)
    }
}

@Serializable
/**
 * Parameter config
 */
internal data class ParameterConfigDto(
    @SerialName("parameter_name") val parameterName: String,
    @SerialName("allowed_type") val allowedType: AllowedTypeDto
)

@Serializable
/**
 * Allowed type
 */
internal enum class AllowedTypeDto {
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

internal fun UrlConfigurationsDto.toCore() = UrlConfigurations(
    name,
    UrlConfiguration(
        allowedHost = configuration.allowedHost,
        allowedUrls = configuration.allowedUrls,
        allowAnyParameter = configuration.allowAnyParameter,
        allowAnyUrl = configuration.allowAnyUrl,
        allowParameters = configuration.allowParameters.map {
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
    )
)
