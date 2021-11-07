package io.github.dllewellyn.safetorun.features.debug

/**
 * Strings to be used for reporting if an app is
 * or is not debuggable
 */
interface DebuggableStrings {
    /**
     * Message to display if found that the app is debuggable
     */
    fun appIsDebuggableMessage(): String

    /**
     * Message to display if app is not debuggable
     */
    fun appIsNotDebuggableMessage(): String

    /**
     * Message to display if debugger is attached
     */
    fun debuggerAttachedMessage(): String

    /**
     * Message to disply if debugger is not attached
     */
    fun debuggerNotAttachedMessage(): String
}
