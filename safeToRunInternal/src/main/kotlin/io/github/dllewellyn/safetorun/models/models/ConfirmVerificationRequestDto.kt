package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable

@Serializable
class ConfirmVerificationRequestDto {
    var signature: String = ""
    var apiKey: String = ""

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ConfirmVerificationRequestDto

        if (signature != other.signature) return false
        if (apiKey != other.apiKey) return false

        return true
    }

    override fun hashCode(): Int {
        var result = signature.hashCode()
        result = 31 * result + apiKey.hashCode()
        return result
    }
}
