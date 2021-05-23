package io.github.dllewellyn.safetorun.backend.repository

interface JwtSecretRepository {
    fun getJwtSecret(): String
}
