package io.github.dllewellyn.safetorun.features.installorigin

internal interface InstallOriginStrings {
    fun couldNotFindPackage(): String
    fun packageWasNotInAllowedList(packageName: String): String
    fun packageWasInAllowedList(): String
}