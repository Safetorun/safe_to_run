package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable

@Serializable
data class DataWrappedVerifyResult(val data: VerifierResult)
