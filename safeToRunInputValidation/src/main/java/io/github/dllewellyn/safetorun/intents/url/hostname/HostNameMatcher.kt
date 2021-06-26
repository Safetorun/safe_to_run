package io.github.dllewellyn.safetorun.intents.url.hostname

import android.net.Uri

/**
 * Get the host name matcher
 */
fun interface HostNameMatcher {

    /**
     * Get the host name given a URL
     *
     * @param url the url to get a host for
     *
     * @return the host for this URL
     */
    fun getHostName(url: String): String?
}

/**
 * Host name matcher based on uri
 */
val hostNameMatcher = HostNameMatcher { Uri.parse(it).host }
