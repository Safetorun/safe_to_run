package com.safetorun.inputverification.model

/**
 * Configuration from file configurations
 */
data class FileConfigurations(val name: String, val configuration: FileConfiguration)

/**
 * Configuration from parent configuration
 */
data class ParentConfiguration(
    val directory: String,
    val allowSubdirectories: Boolean
)

/**
 * Specific file configuration
 */
data class FileConfiguration(
    val allowAnyFile: Boolean,
    val allowedExactFiles: List<String>,
    val allowedParentDirectories: List<ParentConfiguration>,
)


/**
 * URL configuration
 */
data class UrlConfigurations(val name: String, val configuration: UrlConfiguration)

/**
 * URL configuration
 */
data class UrlConfiguration(
    val allowedHost: List<String>,
    val allowedUrls: List<String>,
    val allowParameters: List<ParameterConfig>,
    val allowAnyParameter: Boolean,
    val allowAnyUrl: Boolean
)

/**
 * Parameter config
 */
data class ParameterConfig(
    val parameterName: String,
    val allowedType: AllowedTypeCore
)

/**
 * Allowed type
 */
sealed class AllowedTypeCore {
    /**
     * Any type allowed
     */
    object Any : AllowedTypeCore()

    /**
     * Only string allowed
     */
    object String : AllowedTypeCore()

    /**
     * Only boolean allowed
     */
    object Boolean : AllowedTypeCore()

    /**
     * Only int allowed
     */
    object Int : AllowedTypeCore()

    /**
     * Only double allowed
     */
    object Double : AllowedTypeCore()
}

/**
 * Class to configuration safe to runSafeToRunInputVerificationParserTest input verification
 */
data class SafeToRunInputVerification(
    /**
     * List of url verification configurations
     */
    val urlConfigurations: List<UrlConfigurations>,
    /**
     * List of file verification configurations
     */
    val fileConfiguration: List<FileConfigurations>
)
