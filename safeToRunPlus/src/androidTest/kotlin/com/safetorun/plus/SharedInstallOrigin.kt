package com.safetorun.plus

import android.os.Build
import com.safetorun.features.oscheck.OSInformationQuery
import io.mockk.every

internal object SharedInstallOrigin {

    const val PACKAGE_NAME_RETURNS = "com.package.name"
    const val INSTALLER_PACKAGE = "com.installer.package"
}


internal fun setOlderAndroidVersion(osInformation : OSInformationQuery) {
    every { osInformation.osVersion() } returns Build.VERSION_CODES.LOLLIPOP
}


internal fun setTiramusu(osInformation : OSInformationQuery) {
    every { osInformation.osVersion() } returns Build.VERSION_CODES.TIRAMISU
}
