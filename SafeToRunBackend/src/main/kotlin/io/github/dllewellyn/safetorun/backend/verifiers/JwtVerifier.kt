package io.github.dllewellyn.safetorun.backend.verifiers

import io.github.dllewellyn.safetorun.models.models.VerifierResult

internal interface JwtVerifier {
    fun verifyJwt(jwt: String): VerifierResult
}
