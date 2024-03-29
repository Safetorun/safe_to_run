package com.safetorun.models.core

/**
 * Wrapped to hold the signature of a device check. Decode using any JWT tool
 *
 * @param signature the JWT signature
 */
data class DeviceSignature(val signature: String)
