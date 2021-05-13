package io.github.dllewellyn.safetorun.backend.builder

import io.github.dllewellyn.safetorun.backend.verifiers.JwtVerifier

interface JwtVerifierFactory {
    fun verifierForApiKey(apiKey : String) : JwtVerifier
}
