package com.safetorun.plus

import android.os.Build

internal object SharedInstallOrigin {

    const val PACKAGE_NAME_RETURNS = "com.package.name"
    const val INSTALLER_PACKAGE = "com.installer.package"
}


internal fun setOlderAndroidVersion() {
    mockBuildField(
        Build.VERSION_CODES.N,
        "SDK_INT",
        Build.VERSION::class.java
    )
}

internal fun setTiramusu() {
    mockBuildField(
        Build.VERSION_CODES.TIRAMISU,
        "SDK_INT",
        Build.VERSION::class.java
    )
}
