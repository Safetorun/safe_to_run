package io.github.dllewellyn.safetorun.intents.url

import android.net.Uri
import io.github.dllewellyn.safetorun.intents.url.params.ParameterConfig
import io.github.dllewellyn.safetorun.intents.url.params.ParameterConfigBuilder
import io.github.dllewellyn.safetorun.intents.url.params.matchesAllowedType


class UrlConfig {

    private val allowedHost = mutableListOf<String>()
    private val allowedUrls = mutableListOf<String>()
    private val allowParameters = mutableListOf<ParameterConfig>()
    private var allowAnyParameter = false
    private var allowAnyUrl = false

    fun String.allowHost() {
        allowedHost.add(this)
    }

    fun String.allowUrl() {
        allowedUrls.add(this)
    }

    fun allowParameter(parameter: ParameterConfig) {
        this.allowParameters.add(parameter)
    }

    fun allowAnyParameter() {
        allowAnyParameter = true
    }

    fun allowAnyUrl() {
        allowAnyUrl = true
    }

    fun allowParameter(parameter: ParameterConfigBuilder.() -> Unit) {
        ParameterConfigBuilder()
            .apply(parameter)
            .run { parameterName?.let { ParameterConfig(it, allowedType) } }
            ?.also { allowParameters.add(it) }
    }

    internal fun verify(url: String): Boolean {
        val uri = Uri.parse(url)

        return allowAnyUrl || (url.urlsAreInAllowedLists()
                && (
                uri.queryParameterNames.isEmpty()
                        || allowAnyParameter
                        || uri.allParametersAccountFor()
                ))
    }

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

}

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
    UrlConfig()
        .apply(config)
        .verify(this)
