package io.github.dllewellyn.safetorun.features.debug

/**
 * Implement to determine if an application is debuggable or not
 */
interface IsDebuggable {

    /**
     * Is this app debuggable?
     */
    fun isDebuggable(): Boolean

    /**
     * Is a debugger currently attached?
     */
    fun isDebuggerAttached(): Boolean
}
