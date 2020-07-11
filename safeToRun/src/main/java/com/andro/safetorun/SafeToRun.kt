package com.andro.safetorun

object SafeToRun {

    lateinit var configuration: SafeToRunConfiguration

    /**
     * Initialise with context
     */
    fun init(configuration: SafeToRunConfiguration) {
        this.configuration = configuration
    }

    /**
     * Check if it is safe to run the application
     */
    fun isSafeToRun(): Boolean {
        return configuration.build().canRun(configuration.context)
    }
}