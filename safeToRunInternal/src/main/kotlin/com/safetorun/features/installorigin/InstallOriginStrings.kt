package com.safetorun.features.installorigin

/**
 * Strings to display for install origin checks
 */
interface InstallOriginStrings {

    /**
     * Message to display if we could not find a package
     */
    fun couldNotFindPackage(): String

    /**
     * Message to display if the package was not allowed in the list
     */
    fun packageWasNotInAllowedList(packageName: String): String

    /**
     * Message to show if package WAS in allowed list (i.e. it is ok)
     */
    fun packageWasInAllowedList(): String
}
