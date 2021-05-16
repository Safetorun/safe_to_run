package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable

@Serializable
data class VerifierResult(
    val correctIssuer: Boolean,
    val anyFailures: Boolean,
    val correctSignature: Boolean,
    val expired: Boolean
)
