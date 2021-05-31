package io.github.dllewellyn.safetorun.backend.features.signature

import io.github.dllewellyn.safetorun.features.signatureverify.SignatureVerificationStrings
import io.micronaut.context.annotation.Value
import javax.inject.Singleton

@Singleton
internal class OffDeviceSignatureVerificationStrings(
    @Value("\${safe.to.run.signature_verification}")
    private val verifySignaturePass: String,
    @Value("\${safe.to.run.signature_verification_failure}")
    private val genericFailMessage: String,
    @Value("\${safe.to.run.signature_verification_not_found}")
    private val signatureNotFound: String
) : SignatureVerificationStrings {

    override fun signatureMatchesMessage(): String {
        return verifySignaturePass
    }

    override fun signatureNotFoundMessage(): String {
        return signatureNotFound
    }

    override fun signatureNotMatchedMessage(actualSignature: String): String {
        return genericFailMessage.format(actualSignature)
    }
}
