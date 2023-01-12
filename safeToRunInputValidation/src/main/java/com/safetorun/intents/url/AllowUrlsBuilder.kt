package com.safetorun.intents.url

import com.safetorun.inputverification.builders.UrlConfigurationBuilder
import java.lang.Deprecated

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
     * @deprecated
     *  This method will be removed in version 3.0, use addConfiguration instead
     *
     * @receiver the host to allow
     */
    @Deprecated
    fun UrlConfig.addConfiguration()
    
    /**
    * Add configuration for a URL
    *
    * @param bloc a builder for URL configuration
    */
    fun urlConfig(bloc: UrlConfigVerifier)
    
    /**
     * Allow using+ function to add a configuration
     * @deprecated
     *  This method will be removed in version 3.0, use addConfiguration instead
     *
     * @receiver the URL Config to add
     */
    @Deprecated
    operator fun UrlConfig.unaryPlus()
}
