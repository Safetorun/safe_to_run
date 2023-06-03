package com.safetorun.features.installorigin

import android.content.res.Resources
import com.safetorun.R
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.every
import io.mockk.mockk

object SharedInstallOrigin {

    const val NOT_FOUND = "NOT FOUND"
    const val NOT_MATCHING = "Not matching %s"
    const val MATCHED = "Matched"
    const val PACKAGE_NAME_RETURNS = "com.package.name"

    fun setupAMockResources(): Resources {
        val resources = mockk<Resources>()

        every { resources.getString(R.string.could_not_find_package) } returns NOT_FOUND
        every { resources.getString(R.string.package_was_not_in_allowed_List, any()) } answers {
            NOT_MATCHING.format(args[1].toStr().removePrefix("[").removeSuffix("]"))
        }
        every { resources.getString(R.string.package_was_in_allowed_list) } returns MATCHED

        return resources
    }
}
