package io.github.dllewellyn.safetorun.features.installorigin

/**
 * Query for which app installed this app (e.g. playstore or amazon app store)
 */
interface InstallOriginQuery {
    /**
     * Get the install package name
     *
     * @return install poackage name or null if not
     * found
     */
    fun getInstallPackageName(): String?
}
