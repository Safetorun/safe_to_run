package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable

@Serializable
/**
 * The DTO for sending blacklisted apps to the server
 */
class BlacklistedAppsDto {
    var installedPackages: List<String> = emptyList()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BlacklistedAppsDto

        if (installedPackages != other.installedPackages) return false

        return true
    }

    override fun hashCode(): Int {
        return installedPackages.hashCode()
    }

    override fun toString(): String {
        return "BlacklistedAppsDto(installedPackages=$installedPackages)"
    }
}
