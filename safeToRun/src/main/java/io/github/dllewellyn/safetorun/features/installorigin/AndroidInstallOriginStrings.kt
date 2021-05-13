package io.github.dllewellyn.safetorun.features.installorigin

import android.content.Context
import io.github.dllewellyn.safetorun.R
import io.github.dllewellyn.safetorun.reporting.BaseAndroidStrings

internal class AndroidInstallOriginStrings(context: Context) : BaseAndroidStrings(context), InstallOriginStrings {
    override fun couldNotFindPackage(): String {
        return resources.getString(R.string.could_not_find_package)
    }

    override fun packageWasNotInAllowedList(packageName: String): String {
        return resources.getString(R.string.package_was_not_in_allowed_List, packageName)
    }

    override fun packageWasInAllowedList(): String {
        return resources.getString(R.string.package_was_in_allowed_list)
    }
}
