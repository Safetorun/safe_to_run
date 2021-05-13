package io.github.dllewellyn.safetorun.features.oscheck

 interface OSInformationQuery {
    fun osVersion(): Int
    fun manufacturer(): String
    fun model(): String
}
