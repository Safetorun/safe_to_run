package com.safetorun.inputverification.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
/**
 * Safe to run input verification
 */
data class SafeToRunInputVerificationDto(
    /**
     * List of url verification configurations
     */
    @SerialName("url_verifications") val urlConfigurations: List<UrlConfigurationsDto> = emptyList(),
    /**
     * List of file verification configurations
     */
    @SerialName("file_verifications") val fileConfiguration: List<FileConfigurationsDto> = emptyList()
)
