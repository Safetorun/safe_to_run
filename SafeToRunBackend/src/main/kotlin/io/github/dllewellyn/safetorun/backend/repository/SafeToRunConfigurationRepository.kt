package io.github.dllewellyn.safetorun.backend.repository

import io.github.dllewellyn.safetorun.backend.configuration.SafeToRunConfigurationDto

internal interface SafeToRunConfigurationRepository {
    fun retrieveRepositoryForKey(key: String): SafeToRunConfigurationDto
    fun storeNewConfigurationForKey(key: String, safeToRunConfigurationDto: SafeToRunConfigurationDto)
}
