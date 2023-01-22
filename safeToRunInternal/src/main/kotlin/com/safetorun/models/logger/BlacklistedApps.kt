package com.safetorun.models.logger

/**
 * Check a list of all installed packages
 */
data class BlacklistedApps(
    val installedPackages: List<String> = emptyList()
)
