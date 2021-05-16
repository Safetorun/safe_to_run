package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
class ConfirmVerificationRequestDto {
    var signature: String = ""
    var apiKey: String = ""
}

fun ConfirmVerificationRequestDto.serialize() = Json.encodeToString(this)
