package io.github.dllewellyn.safetorun.backend.generators

import io.github.dllewellyn.safetorun.models.models.SafeToRunResult

/**
 * Generate a JWT
 */
interface JwtGenerator {

    /**
     * Generate a secret for a safe to run result
     *
     * @param safeToRunResult the result to generate a secret for
     */
    fun generateSecretFor(safeToRunResult: SafeToRunResult): String
}
