package io.github.dllewellyn.safetorun.backend.repository

internal interface JwtSecretRepository {
    fun getJwtSecret(): String
}
