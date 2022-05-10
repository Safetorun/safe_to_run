package com.safetorun.features.installorigin

/**
 * Query for which app installed this app (e.g. playstore or amazon app store)
 */
fun interface InstallOriginQuery {
    /**
     * Get the install package name
     *
     * @return install poackage name or null if not
     * found
     */
    fun getInstallPackageName(): String?
}
