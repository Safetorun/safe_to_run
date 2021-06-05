package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable

@Serializable
/**
 * DTO To use for signature verification
 */
data class SignatureVerificationDto(var signatureVerificationString: String? = null)
