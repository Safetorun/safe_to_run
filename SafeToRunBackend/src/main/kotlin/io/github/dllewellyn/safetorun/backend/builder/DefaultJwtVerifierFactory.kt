package io.github.dllewellyn.safetorun.backend.builder

import io.github.dllewellyn.safetorun.backend.utils.JwtFactory
import io.github.dllewellyn.safetorun.backend.verifiers.DefaultJwtVerifier
import io.github.dllewellyn.safetorun.backend.verifiers.JwtVerifier
import javax.inject.Singleton

@Singleton
class DefaultJwtVerifierFactory(private val jwtFactory: JwtFactory) : JwtVerifierFactory {
    override fun verifierForApiKey(apiKey: String): JwtVerifier {
        return DefaultJwtVerifier(apiKey, jwtFactory)
    }
}
