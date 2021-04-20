package com.andro.safetorun.features.signatureverify

interface SignatureVerificationStrings {
    fun signatureMatchesMessage(): String
    fun signatureNotFoundMessage(): String
    fun signatureNotMatchedMessage(actualSignature: String): String
}
