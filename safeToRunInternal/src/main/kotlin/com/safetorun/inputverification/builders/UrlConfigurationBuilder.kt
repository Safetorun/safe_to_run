package com.safetorun.inputverification.builders

import com.safetorun.inputverification.dto.ParameterConfigDto
import com.safetorun.inputverification.dto.UrlConfigurationDto
import com.safetorun.inputverification.dto.UrlConfigurationsDto

/**
 * Class for building a URL
 */
class UrlConfigurationBuilder internal constructor(val name: String) {

    private val allowedHost: List<String> = emptyList()
    private val allowedUrls: List<String> = emptyList()
    private val allowParameters: List<ParameterConfigDto> = emptyList()
    var allowAnyParameter: Boolean = false
    var allowAnyUrl: Boolean = false

    internal fun build() = UrlConfigurationsDto(
        name, UrlConfigurationDto(
            allowedHost,
            allowedUrls,
            allowParameters,
            allowAnyParameter,
            allowAnyUrl,
        )
    )
}
