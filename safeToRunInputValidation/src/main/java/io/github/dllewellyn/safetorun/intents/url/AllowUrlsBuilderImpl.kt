package io.github.dllewellyn.safetorun.intents.url

import android.content.Intent
import io.github.dllewellyn.safetorun.intents.gatherAllStrings

internal class AllowUrlsBuilderImpl(
    private val urlMatcher: UrlMatcher = UrlMatcherImpl()
) : AllowUrlsBuilder {

    override var allowAnyUrls: Boolean = false

    private val allowedHosts = mutableListOf<String>()
    private val allowSpecificUrl = mutableListOf<String>()

    override fun doesUrlCheckPass(intent: Intent) =
        allowAnyUrls ||
                gatherAllStrings(intent)
                    .run {
                        thereArentAnyUrls() || urlsAreInAllowedLists()
                    }

    private fun List<String>.urlsAreInAllowedLists() =
        filterNot { allowSpecificUrl.contains(it) }
            .map(hostNameMatcher::getHostName)
            .filterNot { allowedHosts.contains(it) }.isEmpty()

    private fun List<String>.thereArentAnyUrls() =
        any(urlMatcher::isUrl).not()

    private fun gatherAllStrings(intent: Intent) = intent.extras?.gatherAllStrings() ?: emptyList()

    override fun String.allowHost() {
        allowedHosts.add(this)
    }

    override fun String.allowUrl() {
        allowSpecificUrl.add(this)
    }
}
