package io.github.dllewellyn.safetorun.intents.url

import android.webkit.URLUtil


internal class UrlMatcherImpl : UrlMatcher {
    override fun isUrl(potentialUrl: String): Boolean {
        return URLUtil.isValidUrl(potentialUrl)
    }
}
