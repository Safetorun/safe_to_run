package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable

@Serializable
/**
 * The DTO for sending blacklisted apps to the server
 */
data class BlacklistedAppsDto(
    var installedPackages: List<String> = emptyList()
)
