package io.github.dllewellyn.safetorun.backend.models

import io.micronaut.core.annotation.Introspected

@Introspected
data class SafeToRunResult(
    val failures: Int,
    val successes: Int,
    val warnings: Int,
    val apiKey: String,
    val deviceId: String
) {
    companion object {
        fun empty(apiKey: String, deviceId: String) = SafeToRunResult(
            failures = 0,
            successes = 0,
            warnings = 0,
            apiKey = apiKey,
            deviceId = deviceId
        )
    }
}
