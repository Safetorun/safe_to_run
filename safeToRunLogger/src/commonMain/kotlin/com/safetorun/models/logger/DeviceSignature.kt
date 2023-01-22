package com.safetorun.models.logger

/**
 * Wrapped to hold the signature of a device check. Decode using any JWT tool
 *
 * @param signature the JWT signature
 */
@kotlinx.serialization.Serializable
data class DeviceSignature(val signature: String)
