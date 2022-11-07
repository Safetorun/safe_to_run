package com.safetorun.resilienceshared.dto

import kotlinx.serialization.Serializable

/**
 * Configuration to check if device is rooted
 */
@Serializable
data class RootCheckConfiguration(var tolerateBusyBox: Boolean = false)
