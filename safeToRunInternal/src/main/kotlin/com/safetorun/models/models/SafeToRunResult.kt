package com.safetorun.models.models

/**
 * A safe to run result (off device)
 *
 * @param failures the number of failures
 * @param successes the number of success
 * @param warnings the number of warnings
 * @param apiKey api key that was used
 * @param deviceId the device ID
 */
data class SafeToRunResult(
    val failures: Int,
    val successes: Int,
    val warnings: Int,
    val apiKey: String,
    val deviceId: String
) {
    companion object {
        /**
         * @param apiKey the API key
         * @param deviceId the device id
         */
        fun empty(apiKey: String, deviceId: String) = SafeToRunResult(
            failures = 0,
            successes = 0,
            warnings = 0,
            apiKey = apiKey,
            deviceId = deviceId
        )
    }
}
