package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class DataWrappedVerifyResult(val data: VerifierResult)

fun String.deserializeToDataWrappedVerifyResult() =
    Json.decodeFromString(DataWrappedVerifyResult.serializer(), this)
