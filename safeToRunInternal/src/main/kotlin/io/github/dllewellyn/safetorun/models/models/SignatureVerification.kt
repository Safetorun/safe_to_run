package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable

@Serializable
class SignatureVerification {
    var signatureVerificationString: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SignatureVerification

        if (signatureVerificationString != other.signatureVerificationString) return false

        return true
    }

    override fun hashCode(): Int {
        return signatureVerificationString.hashCode()
    }

    override fun toString(): String {
        return "SignatureVerification(signatureVerificationString='$signatureVerificationString')"
    }
}
