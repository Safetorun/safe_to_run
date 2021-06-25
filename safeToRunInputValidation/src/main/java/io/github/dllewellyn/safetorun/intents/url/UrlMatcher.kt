package io.github.dllewellyn.safetorun.intents.url

internal interface UrlMatcher {
    fun isUrl(potentialUrl : String) : Boolean
}
