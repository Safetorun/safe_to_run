package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable

@Serializable
class ConfirmVerificationRequestDto {
    var signature: String = ""
    var apiKey: String = ""
}
