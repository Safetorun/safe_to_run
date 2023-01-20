package com.safetorun.models.core

/**
 * Check a list of all installed packages
 */
data class BlacklistedApps(
    val installedPackages: List<String> = emptyList()
)
