package com.safetorun.plus.models

import kotlinx.serialization.Serializable

@Serializable
/**
 * Data class which wraps a verifier result with the 'data' tag
 *
 * @param data an [VerifierResult]
 */
data class DataWrappedVerifyResult(val data: VerifierResult)
