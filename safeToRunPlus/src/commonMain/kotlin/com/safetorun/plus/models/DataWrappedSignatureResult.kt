package com.safetorun.plus.models

import com.safetorun.models.models.DeviceSignatureDto
import kotlinx.serialization.Serializable

@Serializable
/**
 * Data class which wraps a device signature with the 'data' tag
 */
data class DataWrappedSignatureResult(val data: DeviceSignatureDto)
