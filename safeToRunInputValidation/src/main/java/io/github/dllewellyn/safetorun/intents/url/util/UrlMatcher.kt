package io.github.dllewellyn.safetorun.intents.url.util

internal interface UrlMatcher {
    fun isUrl(potentialUrl : String) : Boolean
}
