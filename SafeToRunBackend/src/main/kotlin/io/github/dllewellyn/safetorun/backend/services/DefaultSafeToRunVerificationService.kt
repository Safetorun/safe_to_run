package io.github.dllewellyn.safetorun.backend.services

import io.github.dllewellyn.safetorun.backend.builder.JwtVerifierFactory
import io.github.dllewellyn.safetorun.models.models.ConfirmVerificationRequestDto
import io.github.dllewellyn.safetorun.models.models.VerifierResult
import javax.inject.Singleton

@Singleton
internal class DefaultSafeToRunVerificationService(private val jwtVerifier: JwtVerifierFactory) :
    SafeToRunVerificationService {

    override fun verify(confirmationVerification: ConfirmVerificationRequestDto): VerifierResult {
        return jwtVerifier
            .verifierForApiKey(confirmationVerification.apiKey)
            .verifyJwt(confirmationVerification.signature)
    }
}
