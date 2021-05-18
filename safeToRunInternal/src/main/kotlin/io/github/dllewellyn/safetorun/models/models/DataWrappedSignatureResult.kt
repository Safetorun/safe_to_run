package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable

@Serializable
data class DataWrappedSignatureResult(val data: DeviceSignatureDto)
