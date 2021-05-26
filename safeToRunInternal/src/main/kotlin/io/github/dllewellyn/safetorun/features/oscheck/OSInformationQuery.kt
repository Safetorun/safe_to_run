package io.github.dllewellyn.safetorun.features.oscheck

interface OSInformationQuery {
    fun osVersion(): Int
    fun manufacturer(): String
    fun model(): String
    fun board(): String
    fun bootloader(): String
    fun cpuAbi(): List<String>
    fun host(): String
    fun hardware(): String
    fun device(): String
}
