package io.github.dllewellyn.safetorun.intents.url

internal class AllowUrlsBuilderImpl internal constructor(
    private val urlMatcher: UrlMatcher = UrlMatcherImpl()
) : AllowUrlsBuilder {

    private val allowedHosts = mutableListOf<String>()
    private val allowSpecificUrl = mutableListOf<String>()
    override var allowAnyUrls: Boolean = false

    override fun doesUrlCheckPass(listOfStrings: List<String>): Boolean {
        return allowAnyUrls ||
                listOfStrings
                    .run {
                        thereArentAnyUrls() || urlsAreInAllowedLists()
                    }
    }

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

/**
 * Verify URLs
 *
 * @param allowUrlsBuilder a builder configuration
 *
 * @receiver a list of strings to check
 *
 * @return true if this URL is ok, false otherwise
 */
fun List<String>.verifyUrls(allowUrlsBuilder: AllowUrlsBuilder.() -> Unit) =
    (AllowUrlsBuilderImpl() as AllowUrlsBuilder).apply {
        allowUrlsBuilder()
        doesUrlCheckPass(this@verifyUrls)
    }
