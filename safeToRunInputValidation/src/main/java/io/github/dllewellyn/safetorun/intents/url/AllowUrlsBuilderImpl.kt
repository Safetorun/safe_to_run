package io.github.dllewellyn.safetorun.intents.url

internal class AllowUrlsBuilderImpl internal constructor(
    private val urlMatcher: UrlMatcher = UrlMatcherImpl()
) : AllowUrlsBuilder {

    private val allowedHosts = mutableListOf<UrlConfig>()
    override var allowAnyUrls: Boolean = false

    override fun doesUrlCheckPass(listOfStrings: List<String>): Boolean {
        return allowAnyUrls ||
                listOfStrings
                    .run {
                        thereArentAnyUrls() ||
                                filter(urlMatcher::isUrl)
                                    .all { allowedHosts.any { host -> host.verify(it) } }
                    }
    }

    override fun UrlConfig.addConfiguration() {
        allowedHosts.add(this)
    }

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
fun List<String>.verifyUrls(allowUrlsBuilder: AllowUrlsBuilder.() -> Unit) =
    (AllowUrlsBuilderImpl() as AllowUrlsBuilder).apply {
        allowUrlsBuilder()
        doesUrlCheckPass(this@verifyUrls)
    }
