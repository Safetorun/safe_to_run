package com.safetorun.logger.models

/**
 * App metadata
 */
@kotlinx.serialization.Serializable
internal data class AppMetadata(
    val appVersion: String,
    val packageName: String,
    val versionCode: Long,
    val firstInstallTime: Long,
    val lastUpdateTime: Long
) {
    companion object {
        /**
         * Empty app metadata
         */
        fun empty() = AppMetadata("", "", 0, 0, 0)
    }
}

