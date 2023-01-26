package com.safetorun.logger.models

@kotlinx.serialization.Serializable
data class AppMetadata(
    val appVersion: String,
    val packageName: String,
    val versionCode: Long,
    val firstInstallTime: Long,
    val lastUpdateTime: Long
) {
    companion object {
        fun empty() = AppMetadata("", "", 0, 0, 0)
    }
}

