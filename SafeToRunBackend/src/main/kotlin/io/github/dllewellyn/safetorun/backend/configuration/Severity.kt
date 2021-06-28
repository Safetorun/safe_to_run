package io.github.dllewellyn.safetorun.backend.configuration

/**
 * Severeity
 */
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
