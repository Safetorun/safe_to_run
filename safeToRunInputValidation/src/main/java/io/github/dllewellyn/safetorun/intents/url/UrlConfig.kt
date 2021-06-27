package io.github.dllewellyn.safetorun.intents.url

import io.github.dllewellyn.safetorun.intents.url.params.ParameterConfig
import io.github.dllewellyn.safetorun.intents.url.params.ParameterConfigBuilder

/**
 * Url configuration
 */
interface UrlConfig {
    /**
     * Allow a host
     *
     * @receiver the host to allow
     */
    fun String.allowHost()

    /**
     * Allow a URL
     *
     * @receiver allow a url
     */
    fun String.allowUrl()

    /**
     * Allow a parameter
     *
     * @param parameter configuration
     */
    fun allowParameter(parameter: ParameterConfig)

    /**
     * Allow any parameters
     *
     * Warning - if possible, specify the parameters using `allowParameter(ParameterConfig)`
     */
    fun allowAnyParameter()

    /**
     * Allow any url
     *
     * Warning - if possible, specify a specific URL or a Host using `allowHost` and `allowUrl`
     */
    fun allowAnyUrl()

    /**
     * Allow a parameter
     *
     * Example:
     *
     * ```
     * allowParameter {
     *      parameterName = "queryparam"
     *      allowedType = AllowedType.Boolean
     * }
     * ```
     * @param parameter the configuration to use
     */
    fun allowParameter(parameter: ParameterConfigBuilder.() -> Unit)

    /**
     * Verify a URL
     *
     * @param url a url
     *
     * @return true if the URL is valid for use, false otherwise
     */
    fun verify(url: String): Boolean
}
