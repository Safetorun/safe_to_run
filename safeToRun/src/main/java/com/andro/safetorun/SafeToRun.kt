package com.andro.safetorun

import android.content.Context
import com.andro.safetorun.di.SafeToRunDefault
import com.andro.safetorun.di.SafeToRunFactory


object SafeToRun {

    lateinit var context: Context

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
    fun init(context: Context) {
        this.context = context
    }

    /**
     * Check if it is safe to run the application
     */
    fun isSafeToRun(): Boolean {
        return safeToRunFactory?.rootDetection(context)?.isRooted() ?: true
    }
}