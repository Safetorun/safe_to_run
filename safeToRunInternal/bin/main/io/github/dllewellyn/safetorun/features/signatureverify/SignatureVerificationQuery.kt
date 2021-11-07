package io.github.dllewellyn.safetorun.features.signatureverify

/**
 * Verify the signature of a verification query
 */
fun interface SignatureVerificationQuery {
    /**
     * Retrieve the signature for the application
     *
     * @return the signature or null if not available
     */
    fun retrieveSignatureForApplication(): String?
}
