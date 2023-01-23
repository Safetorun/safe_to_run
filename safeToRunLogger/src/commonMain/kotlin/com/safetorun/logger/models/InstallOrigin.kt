package com.safetorun.logger.models

/**
 * Object For the install origin (i.e. which app store installed this app)
 */
@kotlinx.serialization.Serializable
data class InstallOrigin(val installOriginPackageName: String)
