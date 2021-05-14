package io.github.dllewellyn.safetorun.backend.generators

import io.github.dllewellyn.safetorun.models.models.SafeToRunResult

interface JwtGenerator {
    fun generateSecretFor(safeToRunResult: SafeToRunResult): String
}
