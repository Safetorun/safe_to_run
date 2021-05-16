package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class DataWrappedSignatureResult(val data: DeviceSignatureDto)

fun String.deserializeToWrappedResult() = Json.decodeFromString(DataWrappedSignatureResult.serializer(), this)
