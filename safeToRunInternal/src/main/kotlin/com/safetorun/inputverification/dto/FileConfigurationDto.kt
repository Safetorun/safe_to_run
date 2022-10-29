package com.safetorun.inputverification.dto

import com.safetorun.inputverification.model.FileConfiguration
import com.safetorun.inputverification.model.FileConfigurations
import com.safetorun.inputverification.model.ParentConfiguration
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
/**
 * Configuration from file configurations
 */ 
data class FileConfigurationsDto(val name: String, val configuration: FileConfigurationDto)

@Serializable
/**
 * Configuration from parent configuration
 */
 data class ParentConfigurationDto(
    @SerialName("directory") val directory: String,
    @SerialName("allowSubdirectories") val allowSubdirectories: Boolean
)

@Serializable
/**
 * Specific file configuration
 */
 data class FileConfigurationDto(
    @SerialName("allow_any_file") val allowAnyFile: Boolean,
    @SerialName("allowed_exact_files") val allowedExactFiles: List<String>,
    @SerialName("allowed_parent_directories") val allowedParentDirectories: List<ParentConfigurationDto>,
)

 fun FileConfigurationsDto.toCore() = FileConfigurations(
    name,
    FileConfiguration(
        allowAnyFile = configuration.allowAnyFile,
        allowedExactFiles = configuration.allowedExactFiles,
        allowedParentDirectories = configuration.allowedParentDirectories.map {
            ParentConfiguration(it.directory, it.allowSubdirectories)
        }
    )
)
