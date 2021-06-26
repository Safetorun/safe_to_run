package io.github.dllewellyn.safetorun.intents.url

internal class AllowUrlsBuilderImpl(
    private val urlMatcher: UrlMatcher = UrlMatcherImpl()
) : AllowUrlsBuilder {

    override var allowAnyUrls: Boolean = false

    override fun doesUrlCheckPass(listOfStrings: List<String>): Boolean {
        return allowAnyUrls ||
                listOfStrings
                    .run {
                        thereArentAnyUrls() || urlsAreInAllowedLists()
                    }
    }

    private val allowedHosts = mutableListOf<String>()
    private val allowSpecificUrl = mutableListOf<String>()

    private fun List<String>.urlsAreInAllowedLists() =
        filterNot { allowSpecificUrl.contains(it) }
            .map(hostNameMatcher::getHostName)
            .filterNot { allowedHosts.contains(it) }.isEmpty()

    private fun List<String>.thereArentAnyUrls() =
        any(urlMatcher::isUrl).not()

    override fun String.allowHost() {
        allowedHosts.add(this)
    }

    override fun String.allowUrl() {
        allowSpecificUrl.add(this)
    }
}
