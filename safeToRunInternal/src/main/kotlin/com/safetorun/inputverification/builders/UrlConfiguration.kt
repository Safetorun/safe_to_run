package com.safetorun.inputverification.builders

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UrlConfigurationsDto(val name: String, val configuration: UrlConfigurationDto)

@Serializable
data class UrlConfigurationDto(
    @SerialName("allowed_hosts") val allowedHost: List<String>,
    @SerialName("allowed_urls") val allowedUrls: List<String>,
    @SerialName("allowed_parameters") val allowParameters: List<ParameterConfigDto>,
    @SerialName("allow_any_parameter") val allowAnyParameter: Boolean,
    @SerialName("allow_any_url") val allowAnyUrl: Boolean
)

@Serializable
data class ParameterConfigDto(
    @SerialName("parameter_name") val parameterName: String,
    @SerialName("allowed_type") val allowedType: AllowedTypeDto
)

@Serializable
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
