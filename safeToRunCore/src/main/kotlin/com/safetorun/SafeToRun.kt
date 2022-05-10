package com.safetorun

import com.safetorun.reporting.SafeToRunReport

/**
 * Easy access to a safe to run check from anywhere in code
 */
object SafeToRun : SafeToRunLogic {

    private lateinit var configuration: SafeToRunConfiguration

    /**
     * Initialise with configuration
     */
    fun init(configuration: SafeToRunConfiguration) {
        SafeToRun.configuration = configuration
    }

    /**
     * Initialise with configuration block
     */
    fun init(configuration: () -> SafeToRunConfiguration) {
        init(configuration())
    }

    /**
     * Check if it is safe to run the application
     */
    override fun isSafeToRun(): SafeToRunReport {
        return configuration.build().canRun()
    }
}
