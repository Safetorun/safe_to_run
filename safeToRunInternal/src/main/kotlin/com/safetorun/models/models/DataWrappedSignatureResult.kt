package com.safetorun.models.models

import kotlinx.serialization.Serializable

@Serializable
/**
 * Data class which wraps a device signature with the 'data' tag
 */
data class DataWrappedSignatureResult(val data: DeviceSignatureDto)
