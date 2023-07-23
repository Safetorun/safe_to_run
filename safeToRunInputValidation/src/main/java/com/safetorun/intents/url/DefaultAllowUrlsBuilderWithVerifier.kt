package com.safetorun.intents.url

import com.safetorun.intents.BaseVerifier
import com.safetorun.intents.url.util.UrlMatcher
import com.safetorun.intents.url.util.UrlMatcherImpl


/**
 * Interface for building an allows URL but with a verifier
 */
internal class DefaultAllowUrlsBuilderWithVerifier(
    private val urlMatcher: UrlMatcher = UrlMatcherImpl()
) : AllowUrlsBuilderWithVerifier,
    AllowUrlsBuilder by DefaultAllowUrlsBuilder(urlMatcher),
    BaseVerifier<List<String>>() {
    override fun internalVerify(input: List<String>): Boolean {
        return doesUrlCheckPass(input)
    }
}

internal class DefaultAllowUrlsBuilder(
    private val urlMatcher: UrlMatcher = UrlMatcherImpl()
) : AllowUrlsBuilder {

    private val allowedHosts = mutableListOf<UrlConfig>()
    override var allowAnyUrls: Boolean = false

    override fun doesUrlCheckPass(listOfStrings: List<String>): Boolean {
        return allowAnyUrls
                || listOfStrings.thereArentAnyUrls()
                || listOfStrings.filterUrls().areAllUrlsInAllowedHosts()
    }

    override fun UrlConfig.addConfiguration() {
        allowedHosts.add(this)
    }

    override fun urlConfig(bloc: UrlConfigVerifier) {
        allowedHosts.add(UrlConfigImpl().apply(bloc))
    }

    override fun UrlConfig.unaryPlus() {
        addConfiguration()
    }

    private fun List<String>.areAllUrlsInAllowedHosts() =
        all { allowedHosts.any { host -> host.verify(it) } }

    private fun List<String>.filterUrls() =
        filter(urlMatcher::isUrl)

    private fun List<String>.thereArentAnyUrls() =
        any(urlMatcher::isUrl).not()
}

/**
 * Verify URLs
 *
 * @param allowUrlsBuilder a builder configuration
 *
 * @receiver a list of strings to check
 *
 * @return true if this URL is ok, false otherwise
 */
fun List<String>.verifyUrls(allowUrlsBuilder: AllowUrlsBuilderWithVerifier.() -> Unit) =
    (DefaultAllowUrlsBuilderWithVerifier()).run {
        allowUrlsBuilder()
        verify(this@verifyUrls)
    }
