package com.safetorun.inputverification.builders.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
/**
 * Safe to run input verification
 */
internal data class SafeToRunInputVerificationDto(
    /**
     * List of url verification configurations
     */
    @SerialName("url_verifications") val urlConfigurations: List<UrlConfigurationsDto>,
    /**
     * List of file verification configurations
     */
    @SerialName("file_verifications") val fileConfiguration: List<FileConfigurationsDto>
)
