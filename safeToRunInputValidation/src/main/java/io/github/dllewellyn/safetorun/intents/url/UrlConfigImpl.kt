package io.github.dllewellyn.safetorun.intents.url

import android.net.Uri
import io.github.dllewellyn.safetorun.intents.url.hostname.hostNameMatcher
import io.github.dllewellyn.safetorun.intents.url.params.ParameterConfig
import io.github.dllewellyn.safetorun.intents.url.params.ParameterConfigBuilder
import io.github.dllewellyn.safetorun.intents.url.params.matchesAllowedType


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

        return uri.shouldHandle().not() || allowAnyUrl || (url.urlsAreInAllowedLists()
                && (
                uri.queryParameterNames.isEmpty()
                        || allowAnyParameter
                        || uri.allParametersAccountFor()
                ))
    }

    private fun Uri.shouldHandle() = host != null

    private fun Uri.allParametersAccountFor(): Boolean {
        return queryParameterNames.map { actualParam ->
            Pair(
                actualParam,
                allowParameters.firstOrNull { it.parameterName == actualParam })
        }
            .any {
                it.second?.allowedType?.matchesAllowedType(getQueryParameter(it.first)) == true
            }
    }

    private fun String.urlsAreInAllowedLists() =
        allowedUrls.contains(this) ||
                allowedHost.contains(hostNameMatcher.getHostName(this))

    private fun ParameterConfigBuilder.toConfig() =
        parameterName?.let { ParameterConfig(it, allowedType) }
}

/**
 * Create a URL configuration
 *
 * @param config the url configuration
 *
 * @return a URL Config
 */
fun urlConfiguration(config: UrlConfig.() -> Unit) =
    (UrlConfigImpl() as UrlConfig).apply(config)


/**
 * Verify a URL matches the configuration
 *
 * @param config the configuration
 *
 * @receiver string url
 *
 * @return true if the check failed, false otherwise
 */
fun String.urlVerification(config: UrlConfig.() -> Unit) =
    (UrlConfigImpl() as UrlConfig)
        .apply(config)
        .verify(this)
