package io.github.dllewellyn.safetorun.features.signatureverify

import io.github.dllewellyn.safetorun.checks.SafeToRunCheck

fun verifySignatureConfiguration(
    signatureVerificationStrings: SignatureVerificationStrings,
    signatureVerificationQuery: SignatureVerificationQuery,
    vararg signature: String
): SafeToRunCheck {
    return SignatureVerificationCheck(
        signature.toList(),
        signatureVerificationStrings = signatureVerificationStrings,
        signatureVerificationQuery = signatureVerificationQuery
    )
}
