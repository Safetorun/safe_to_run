package com.andro.safetorun

import android.content.Context

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
    fun isSafeToRun(context : Context): Boolean {
        return configuration.build().canRun(context)
    }
}