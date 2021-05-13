package io.github.dllewellyn.safetorun.backend.services

import io.github.dllewellyn.safetorun.backend.models.ConfirmVerificationRequestDto
import io.github.dllewellyn.safetorun.backend.verifiers.VerifierResult

interface SafeToRunVerificationService {
    fun verify(confirmationVerification: ConfirmVerificationRequestDto): VerifierResult
}
