package io.github.dllewellyn.safetorun.backend.services

import io.github.dllewellyn.safetorun.models.models.ConfirmVerificationRequestDto
import io.github.dllewellyn.safetorun.models.models.VerifierResult

internal interface SafeToRunVerificationService {
    fun verify(confirmationVerification: ConfirmVerificationRequestDto): VerifierResult
}