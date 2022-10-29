package com.safetorun.inputverification.builders

import com.safetorun.inputverification.dto.UrlConfigurationDto
import com.safetorun.inputverification.dto.UrlConfigurationsDto
import com.safetorun.inputverification.dto.toDto
import com.safetorun.inputverification.model.ParameterConfig

/**
 * Class for building a URL
 */
class UrlConfigurationBuilder internal constructor(val name: String) {

    private val allowedHost: MutableList<String> = mutableListOf()
    private val allowedUrls: MutableList<String> = mutableListOf()
    private val allowParameters: MutableList<ParameterConfig> = mutableListOf()

    /**
     * Whether to allow any parameters
     */
    var allowAnyParameter: Boolean = false

    /**
     * Whether to allow any URL
     */
    var allowAnyUrl: Boolean = false

    /**
     * Allow a URL
     */
    fun String.allowUrl() {
        allowedUrls.add(this)
    }

    /**
     * Allow a host
     */
    fun String.allowHost() {
        allowedHost.add(this)
    }

    /**
     * Allow a parameter
     */
    fun ParameterConfig.allowParameter() {
        allowParameters.add(this)
    }

    internal fun build() = UrlConfigurationsDto(
        name, UrlConfigurationDto(
            allowedHost,
            allowedUrls,
            allowParameters.toDto(),
            allowAnyParameter,
            allowAnyUrl,
        )
    )
}
