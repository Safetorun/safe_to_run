package io.github.dllewellyn.safetorun.intents.url

import android.content.Intent

internal interface AllowUrlsBuilder {
    /**
     * Allow any URLS at all in the intent
     */
    var allowAnyUrls: Boolean

    /**
     * Returns if we pass the URL Check based on the config
     *
     * @param intent the intent we're checking
     */
    fun doesUrlCheckPass(intent: Intent): Boolean

    /**
     * Host to allow
     *
     * @receiver the host to allow
     */
    fun String.allowHost()

    /**
     * Allow a specific URL
     *
     * @receiver the url to allow
     */
    fun String.allowUrl()
}