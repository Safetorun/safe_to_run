package com.safetorun.logger.models

/**
 * Check a list of all installed packages
 */
@kotlinx.serialization.Serializable
data class BlacklistedApps(
    val installedPackages: List<String> = emptyList()
)
