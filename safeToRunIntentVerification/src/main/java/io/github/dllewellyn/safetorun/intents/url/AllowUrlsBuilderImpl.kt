package io.github.dllewellyn.safetorun.intents.url

import android.content.Intent
import io.github.dllewellyn.safetorun.intents.gatherAllStrings

internal class AllowUrlsBuilderImpl(
    private val urlMatcher: UrlMatcher = UrlMatcherImpl()
) : AllowUrlsBuilder {

    override var allowAnyUrls: Boolean = false

    private val allowedHosts = mutableListOf<String>()

    override fun doesUrlCheckPass(intent: Intent) =
        allowAnyUrls ||
                gatherAllStrings(intent)
                    .run {
                        thereArentAnyUrls() || urlsAreInAllowedHost()
                    }

    private fun List<String>.urlsAreInAllowedHost() = map(hostNameMatcher::getHostName)
        .filterNot { allowedHosts.contains(it) }.isEmpty()

    private fun List<String>.thereArentAnyUrls() =
        any(urlMatcher::isUrl).not()

    private fun gatherAllStrings(intent: Intent) = intent.extras?.gatherAllStrings() ?: emptyList()

    override fun String.allowHost() {
        allowedHosts.add(this)
    }
}
