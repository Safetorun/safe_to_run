package com.safetorun.backendresilience.dto
/**
 * Severity
 */
@kotlinx.serialization.Serializable
enum class Severity {
    /**
     * Warn if fails
     */
    Warn,

    /**
     * Error if fails
     */
    Error,

    /**
     * Do nothing if fails
     */
    None
}
