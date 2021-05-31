package io.github.dllewellyn.safetorun

import io.github.dllewellyn.safetorun.reporting.SafeToRunReport

/**
 * Implement the logic for safe to run
 */
interface SafeToRunLogic {
    /**
     * Check if it is safe to run the application
     */
    fun isSafeToRun(): SafeToRunReport
}
