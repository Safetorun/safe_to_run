package com.safetorun.intents.url

import android.net.Uri
import com.safetorun.intents.IntentVerificationBuilder
import com.safetorun.intents.url.hostname.hostNameMatcher
import com.safetorun.intents.url.params.ParameterConfig
import com.safetorun.intents.url.params.ParameterConfigBuilder
import com.safetorun.intents.url.params.matchesAllowedType


internal class UrlConfigImpl : UrlConfig {
    private val allowedHost = mutableListOf<String>()
    private val allowedUrls = mutableListOf<String>()
    private val allowParameters = mutableListOf<ParameterConfig>()
    private var allowAnyParameter = false
    private var allowAnyUrl = false

    override fun String.allowHost() {
        allowedHost.add(this)
    }

    override fun String.allowUrl() {
        allowedUrls.add(this)
    }

    override fun allowParameter(parameter: ParameterConfig) {
        this.allowParameters.add(parameter)
    }

    override fun allowAnyParameter() {
        allowAnyParameter = true
    }

    override fun allowAnyUrl() {
        allowAnyUrl = true
    }

    override fun allowParameter(parameter: ParameterConfigBuilder.() -> Unit) {
        ParameterConfigBuilder()
            .apply(parameter)
            .toConfig()
            ?.also { allowParameter(it) }
    }

    override fun verify(url: String): Boolean {
        val uri = Uri.parse(url)

        return uri.host == null || allowAnyUrl || (url.urlsAreInAllowedLists()
                && (

                uri.queryParameterNames.isEmpty()
                        || allowedUrls.contains(url)
                        || allowAnyParameter
                        || uri.allParametersAccountFor()
                ))
    }

    private fun Uri.allParametersAccountFor(): Boolean =
        queryParameterNames.map { actualParam ->
            Pair(
                actualParam,
                allowParameters.firstOrNull { it.parameterName == actualParam })
        }
            .any {
                it.second?.allowedType?.matchesAllowedType(getQueryParameter(it.first)) == true
            }

    private fun String.urlsAreInAllowedLists() =
        allowedUrls.contains(this) ||
                allowedHost.contains(hostNameMatcher.getHostName(this))

    private fun ParameterConfigBuilder.toConfig() =
        parameterName?.let { ParameterConfig(it, allowedType) }
}

/**
 * Alias for url verification builder
 */
typealias UrlConfigVerifier = UrlConfig.() -> Unit

/**
 * Create a URL configuration
 *
 * @param config the url configuration
 *
 * @return a URL Config
 */
fun urlConfiguration(config: UrlConfigVerifier) =
    (UrlConfigImpl() as UrlConfig).apply(config)


/**
 * Verify a URL matches the configuration
 *
 * @param config the configuration
 *
 * @receiver string url
 *
 * @return true if the check passed, false otherwise
 */
fun String.urlVerification(config: UrlConfigVerifier) =
    (UrlConfigImpl() as UrlConfig)
        .apply(config)
        .verify(this)
