package com.andro.safetorun

import com.andro.safetorun.reporting.SafeToRunReport

object SafeToRun {

    private lateinit var configuration: SafeToRunConfiguration

    /**
     * Initialise with context
     */
    fun init(configuration: SafeToRunConfiguration) {
        this.configuration = configuration
    }

    /**
     * Check if it is safe to run the application
     */
    fun isSafeToRun(): SafeToRunReport {
        return configuration.build().canRun()
    }
}