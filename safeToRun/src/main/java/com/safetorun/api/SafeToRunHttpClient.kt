package com.safetorun.api

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy

/**
 * Safe to run HTTP Client
 */
internal interface SafeToRunHttpClient {
    fun <T, O> post(
        path: String,
        headers: Map<String, String>,
        body: T,
        serializer: SerializationStrategy<T>,
        deserializer: DeserializationStrategy<O>
    ): O
}
