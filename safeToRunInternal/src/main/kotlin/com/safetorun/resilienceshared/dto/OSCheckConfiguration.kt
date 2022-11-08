package com.safetorun.resilienceshared.dto

/**
 * Os check configuration
 */
@kotlinx.serialization.Serializable
data class OSCheckConfiguration(var configuration: List<SingleOSCheckConfiguration> = emptyList())

/**
 * OS Check configuration
 */
@kotlinx.serialization.Serializable
data class SingleOSCheckConfiguration(
    var allStringChecks: List<SingleStringCheck> = emptyList(),
    var allIntChecks: List<SingleIntCheck> = emptyList(),
    var unlessIntChecks: List<SingleIntCheck> = emptyList(),
    var unlessStringChecks: List<SingleStringCheck> = emptyList(),
)
