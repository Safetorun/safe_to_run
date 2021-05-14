package io.github.dllewellyn.safetorun.backend.handlers

import io.github.dllewellyn.safetorun.models.models.ConfirmVerificationRequestDto
import io.github.dllewellyn.safetorun.backend.services.SafeToRunVerificationService
import io.github.dllewellyn.safetorun.backend.verifiers.VerifierResult
import io.micronaut.context.annotation.Prototype
import io.micronaut.core.annotation.Introspected
import io.micronaut.function.aws.MicronautRequestHandler
import javax.inject.Inject

@Introspected
@Prototype
class SafeToRunVerifyHandler :
    MicronautRequestHandler<ConfirmVerificationRequestDto, VerifierResult>() {

    @Inject
    lateinit var safeToRun: SafeToRunVerificationService

    override fun execute(input: ConfirmVerificationRequestDto): VerifierResult {
        if (input.signature.isEmpty()) {
            throw IllegalArgumentException("Signature verification is empty")
        }

        return safeToRun.verify(input)
    }
}
