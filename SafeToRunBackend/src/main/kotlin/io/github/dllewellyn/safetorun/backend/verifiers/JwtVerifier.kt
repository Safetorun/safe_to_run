package io.github.dllewellyn.safetorun.backend.verifiers

import io.micronaut.core.annotation.Introspected

@Introspected
data class VerifierResult(
    val correctIssuer: Boolean,
    val anyFailures: Boolean,
    val correctSignature: Boolean,
    val expired: Boolean
)

interface JwtVerifier {
    fun verifyJwt(jwt: String): VerifierResult
}

