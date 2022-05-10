package com.safetorun.intents.url.util

internal interface UrlMatcher {
    fun isUrl(potentialUrl : String) : Boolean
}
