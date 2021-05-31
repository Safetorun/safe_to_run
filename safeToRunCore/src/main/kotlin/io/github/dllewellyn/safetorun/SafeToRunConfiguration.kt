package io.github.dllewellyn.safetorun

import io.github.dllewellyn.safetorun.checks.CompositeSafeToRunCheck
import io.github.dllewellyn.safetorun.checks.SafeToRunCheck

/**
 * Configuration for safe to run. Use this to create your setup for Safe To Run.
 *
 * Sample:
 *
 * <code>
 * ```
 *
 * // Root beer (detect root)
 * rootDetection {
 * tolerateBusyBox = true
 * }.error()
 *
 * // Black list certain apps
 * blacklistConfiguration {
 * +"com.abc.def"
 * +packageName
 * }.error()
 *
 * verifySignatureConfig("cSP1O3JN/8+Ag14WAOeOEnwAnpY=")
 * .error()
 *
 * // OS Blacklist version
 * osDetectionCheck(
 * conditionalBuilder {
 * with(minOsVersion(MIN_OS_VERSION))
 * and(notManufacturer("Abc"))
 * }
 * ).warn()
 *
 * installOriginCheckWithDefaults().warn()
 *
 * debugCheck().warn()
```</code>
 */
class SafeToRunConfiguration internal constructor() {

    private val safeToRunChecks = mutableListOf<SafeToRunCheck>()
    private val safeToRunWarnings = mutableListOf<SafeToRunCheck>()

    private fun add(safeToRunCheck: SafeToRunCheck) {
        safeToRunChecks.add(safeToRunCheck)
    }

    /**
     * Error if a particular check happens
     *
     * @param safeToRunCheck the check to error on
     */
    infix fun errorIf(safeToRunCheck: SafeToRunCheck) {
        add(safeToRunCheck)
    }

    /**
     * Warn if safe to run check fails
     *
     * @receiver the safe to run check
     */
    fun SafeToRunCheck.warn() {
        safeToRunWarnings.add(this)
    }

    /**
     * Error if safe to run check fails
     *
     * @receiver safe to run check
     */
    fun SafeToRunCheck.error() {
        safeToRunChecks.add(this)
    }

    /**
     * Warn if a safe to run check fails
     *
     * @param safeToRunCheck the check to run
     */
    infix fun warnIf(safeToRunCheck: SafeToRunCheck) {
        safeToRunWarnings.add(safeToRunCheck)
    }

    /**
     * Build a safe to run check based on all of the configured checks
     */
    fun build(): SafeToRunCheck = CompositeSafeToRunCheck(safeToRunChecks, safeToRunWarnings)
}

/**
 * Utility function to build a safe to run check
 */
fun configure(
    configuration: SafeToRunConfiguration.() -> Unit
): SafeToRunConfiguration =
    SafeToRunConfiguration().apply {
        configuration()
    }
