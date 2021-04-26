package io.github.dllewellyn.safetorun.features.signatureverify

internal interface SignatureVerificationStrings {
    fun signatureMatchesMessage(): String
    fun signatureNotFoundMessage(): String
    fun signatureNotMatchedMessage(actualSignature: String): String
}
