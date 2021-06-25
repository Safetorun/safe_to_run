package io.github.dllewellyn.safetorun.intents.url

import android.net.Uri

fun interface HostNameMatcher {
    fun getHostName(url: String): String?
}

val hostNameMatcher = HostNameMatcher { Uri.parse(it).host }
