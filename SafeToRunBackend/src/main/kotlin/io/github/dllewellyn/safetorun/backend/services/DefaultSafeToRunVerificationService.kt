package io.github.dllewellyn.safetorun.backend.services

import io.github.dllewellyn.safetorun.backend.builder.JwtVerifierFactory
import io.github.dllewellyn.safetorun.backend.models.ConfirmVerificationRequestDto
import io.github.dllewellyn.safetorun.backend.verifiers.VerifierResult
import javax.inject.Singleton

@Singleton
class DefaultSafeToRunVerificationService(private val jwtVerifier: JwtVerifierFactory) : SafeToRunVerificationService {

    override fun verify(confirmationVerification: ConfirmVerificationRequestDto): VerifierResult {
        return jwtVerifier
            .verifierForApiKey(confirmationVerification.apiKey)
            .verifyJwt(confirmationVerification.signature)
    }
}
