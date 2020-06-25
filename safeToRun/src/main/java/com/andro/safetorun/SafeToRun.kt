package com.andro.safetorun

import com.andro.safetorun.di.SafeToRunDefault
import com.andro.safetorun.di.SafeToRunFactory


object SafeToRun {

    lateinit var configuration: SafeToRunConfiguration

    var safeToRunFactory: SafeToRunFactory? = null
        get() {
            return if (field == null) {
                SafeToRunDefault
            } else {
                field
            }
        }

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