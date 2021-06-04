package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable

@Serializable
/**
 * DTO To use for signature verification
 */
data class SignatureVerification(var signatureVerificationString: String? = null)
