package io.github.dllewellyn.safetorun.backend.models

import io.micronaut.core.annotation.Introspected

@Introspected
class BlacklistedAppsDto {
    var installedPackages: List<String> = emptyList()
}