package com.safetorun.plus.models

import kotlinx.serialization.Serializable

@Serializable
/**
 * DTO For the install origin (i.e. which app store installed this app)
 */
data class InstallOriginDto(var installOriginPackageName: String? = null)
