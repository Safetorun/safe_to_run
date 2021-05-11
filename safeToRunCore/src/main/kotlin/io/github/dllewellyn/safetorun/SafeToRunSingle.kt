package io.github.dllewellyn.safetorun

import io.github.dllewellyn.safetorun.reporting.SafeToRunReport


object SafeToRunSingle : SafeToRun {

    private lateinit var configuration: SafeToRunConfiguration

    /**
     * Initialise with configuration
     */
    fun init(configuration: SafeToRunConfiguration) {
        SafeToRunSingle.configuration = configuration
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