package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable

@Serializable
class BlacklistedAppsDto {
    var installedPackages: List<String> = emptyList()
}
