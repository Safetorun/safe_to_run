package com.safetorun.intents.url

import com.safetorun.intents.SafeToRunVerifier
import com.safetorun.intents.url.params.ParameterConfig
import com.safetorun.intents.url.params.ParameterConfigBuilder

/**
 * Url configuration
 */
interface UrlConfig : SafeToRunVerifier<String> {
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
}
