package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable

@Serializable
/**
 * DTO Used to confirm verification
 */
data class ConfirmVerificationRequestDto(
    var signature: String = "",
    var apiKey: String = ""
)
