package com.safetorun.features.signatureverify

/**
 * Define messages to be shown for signature verification
 */
interface SignatureVerificationStrings {

    /**
     * Message to show if the signature matches the expected
     */
    fun signatureMatchesMessage(): String

    /**
     * Message to show if the signature was not found at all
     */
    fun signatureNotFoundMessage(): String

    /**
     * Message to show if signature did not match the expected signature(s)
     *
     * @param actualSignature the actual signature that we observed
     */
    fun signatureNotMatchedMessage(actualSignature: String): String
}
