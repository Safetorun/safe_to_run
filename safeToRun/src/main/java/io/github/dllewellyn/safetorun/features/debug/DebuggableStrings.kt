package io.github.dllewellyn.safetorun.features.debug

internal interface DebuggableStrings {
    fun appIsDebuggableMessage(): String
    fun appIsNotDebuggableMessage(): String
    fun debuggerAttachedMessage(): String
    fun debuggerNotAttachedMessage(): String
}