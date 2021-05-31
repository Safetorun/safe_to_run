package io.github.dllewellyn.safetorun.backend.handlers

import io.github.dllewellyn.safetorun.models.models.ConfirmVerificationRequestDto
import io.github.dllewellyn.safetorun.backend.services.SafeToRunVerificationService
import io.github.dllewellyn.safetorun.models.models.VerifierResult
import io.micronaut.context.annotation.Prototype
import io.micronaut.core.annotation.Introspected
import io.micronaut.function.aws.MicronautRequestHandler
import javax.inject.Inject

@Introspected
@Prototype
/**
 * Use to verify a signature that was generated. Returns a verifier result
 * [VerifierResult]
 */
class SafeToRunVerifyHandler :
    MicronautRequestHandler<ConfirmVerificationRequestDto, VerifierResult>() {

    @Inject
    internal lateinit var safeToRun: SafeToRunVerificationService

    override fun execute(input: ConfirmVerificationRequestDto): VerifierResult {
        if (input.signature.isEmpty()) {
            throw IllegalArgumentException("Signature verification is empty")
        }

        return safeToRun.verify(input)
    }
}
