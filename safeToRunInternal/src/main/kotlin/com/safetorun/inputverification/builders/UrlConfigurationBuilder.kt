package com.safetorun.inputverification.builders

import com.safetorun.inputverification.dto.ParameterConfigDto
import com.safetorun.inputverification.dto.UrlConfigurationDto
import com.safetorun.inputverification.dto.UrlConfigurationsDto

/**
 * Class for building a URL
 */
class UrlConfigurationBuilder internal constructor(val name: String) {

    private val allowedHost: MutableList<String> = mutableListOf()
    private val allowedUrls: MutableList<String> = mutableListOf()
    private val allowParameters: MutableList<ParameterConfigDto> = mutableListOf()
    var allowAnyParameter: Boolean = false
    var allowAnyUrl: Boolean = false

    fun String.allowUrl() {
        allowedUrls.add(this)
    }

    fun String.allowHost() {
        allowedHost.add(this)
    }



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
