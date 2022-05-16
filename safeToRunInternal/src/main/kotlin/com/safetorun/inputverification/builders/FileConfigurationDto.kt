package com.safetorun.inputverification.builders

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FileConfigurationsDto(val name: String, val configuration: FileConfigurationDto)

@Serializable
data class ParentConfigurationDto(
    @SerialName("directory") val directory: String,
    @SerialName("allowSubdirectories") val allowSubdirectories: Boolean
)

@Serializable
data class FileConfigurationDto(
    @SerialName("allow_any_file") val allowAnyFile: Boolean,
    @SerialName("allowed_exact_files") val allowedExactFiles: List<String>,
    @SerialName("allowed_parent_directories") val allowedParentDirectories: List<ParentConfigurationDto>,
)
