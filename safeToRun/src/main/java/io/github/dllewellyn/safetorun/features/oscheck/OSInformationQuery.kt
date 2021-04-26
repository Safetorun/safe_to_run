package io.github.dllewellyn.safetorun.features.oscheck

internal interface OSInformationQuery {
    fun osVersion(): Int
    fun manufacturer(): String
    fun model(): String
}