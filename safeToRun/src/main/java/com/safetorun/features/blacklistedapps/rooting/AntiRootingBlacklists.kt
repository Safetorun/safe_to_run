package com.safetorun.features.blacklistedapps.rooting

import com.safetorun.features.blacklistedapps.BlacklistedAppConfiguration
import com.safetorun.features.blacklistedapps.rooting.RootingAppConstants.APP_QUARANTINE
import com.safetorun.features.blacklistedapps.rooting.RootingAppConstants.MAGISK
import com.safetorun.features.blacklistedapps.rooting.RootingAppConstants.ROOT_REMOVE_JB
import com.safetorun.features.blacklistedapps.rooting.RootingAppConstants.SU
import com.safetorun.features.blacklistedapps.rooting.RootingAppConstants.SUPER_SU
import com.safetorun.features.blacklistedapps.rooting.RootingAppConstants.SUPER_USER
import com.safetorun.features.blacklistedapps.rooting.RootingAppConstants.SUPER_USER_2

/**
 * Create a blacklisting configuration based on a list of apps
 * which are used for rooting
 */
fun BlacklistedAppConfiguration.blacklistRootingApps() {
    +SUPER_USER
    +SUPER_SU
    +SU
    +SUPER_USER_2
    +ROOT_REMOVE_JB
    +APP_QUARANTINE
    +MAGISK
}

/**
 * Constants for rooted apps
 */
object RootingAppConstants {
    /**
     * App used for rooted phones
     */
    const val SUPER_USER = "com.thirdparty.superuser"

    /**
     * App used for rooted phones
     */
    const val SUPER_SU = "eu.chainfire.supersu"

    /**
     * App used for rooted phones
     */
    const val SU = "com.noshufou.android.su"

    /**
     * App used for rooted phones
     */
    const val SUPER_USER_2 = "com.koushikdutta.superuser"

    /**
     * App used for rooted phones
     */
    const val ROOT_REMOVE_JB = "com.zachspong.temprootremovejb"

    /**
     * App used for rooted phones
     */
    const val APP_QUARANTINE = "com.ramdroid.appquarantine"

    /**
     * App used for rooted phones
     */
    const val MAGISK = "com.topjohnwu.magisk"
}
