package io.github.dllewellyn.safetorun.features.signatureverify

fun interface SignatureVerificationQuery {
    fun retrieveSignatureForApplication(): String?
}
