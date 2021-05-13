package io.github.dllewellyn.safetorun.features.signatureverify

interface SignatureVerificationQuery {
    fun retrieveSignatureForApplication(): String?
}
