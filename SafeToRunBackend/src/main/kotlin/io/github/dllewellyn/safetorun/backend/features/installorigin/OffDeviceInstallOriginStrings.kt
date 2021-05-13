package io.github.dllewellyn.safetorun.backend.features.installorigin

import io.github.dllewellyn.safetorun.features.installorigin.InstallOriginStrings
import io.micronaut.context.annotation.Value
import javax.inject.Singleton

@Singleton
internal class OffDeviceInstallOriginStrings(
    @Value("\${safe.to.run.could_not_find_package}")
    private val couldNotFindPackageString: String,
    @Value("\${safe.to.run.was_not_allowed}")
    private val packageNotAllowedString: String,
    @Value("\${safe.to.run.was_allowed}")
    private val packageWasAllowedString: String
) : InstallOriginStrings {

    override fun couldNotFindPackage(): String {
        return couldNotFindPackageString
    }

    override fun packageWasNotInAllowedList(packageName: String): String {
        return packageNotAllowedString.format(packageName)
    }

    override fun packageWasInAllowedList(): String {
        return packageWasAllowedString
    }
}
