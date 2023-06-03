package com.safetorun.models.models

/**
 * Response with data wrapped
 */
@kotlinx.serialization.Serializable
data class DataWrappedLogResponse<T>(val data: T)
