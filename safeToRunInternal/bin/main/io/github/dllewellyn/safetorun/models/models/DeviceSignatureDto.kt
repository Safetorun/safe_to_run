package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable

@Serializable
/**
 * Wrapped to hold the signature of a device check. Decode using any JWT tool
 *
 * @param signature the JWT signature
 */
data class DeviceSignatureDto(val signature: String)
