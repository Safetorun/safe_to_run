package com.andro.safetorun.features.debug

interface DebuggableStrings {
    fun appIsDebuggableMessage(): String
    fun appIsNotDebuggableMessage(): String
    fun debuggerAttachedMessage(): String
    fun debuggerNotAttachedMessage(): String
}