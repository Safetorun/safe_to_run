package com.andro.safetorun.features.oscheck

interface OSInformationQuery {
    fun osVersion(): Int
    fun manufacturer(): String
    fun model(): String
}