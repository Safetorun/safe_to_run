package com.safetorun.intents.url

/**
 * The builder for allowing URLs to pass the verifiation service
 */
interface AllowUrlsBuilder {
    /**
     * Allow any URLS at all in the intent
     */
    var allowAnyUrls: Boolean

    /**
     * Returns if we pass the URL Check based on the config
     *
     * @param listOfStrings list of strings
     */
    fun doesUrlCheckPass(listOfStrings: List<String>): Boolean

    /**
     * Host to allow
     *
     * @receiver the host to allow
     */
    fun UrlConfig.addConfiguration()
}
