package io.github.dllewellyn.safetorun.features.signatureverify

import io.github.dllewellyn.safetorun.checks.SafeToRunCheck

/**
 * Verify signature configuration
 *
 * @param signatureVerificationStrings strings to use for success or failures
 * @param signatureVerificationQuery a query to run in order to retrieve the query
 *
 * @return a safe to run check that can be called with `isSafeToRun()`
 */
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
