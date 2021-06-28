package io.github.dllewellyn.safetorun.configuration

enum class Severity {
    Warn,
    Error,
    None
}

data class BlacklistedAppConfigurationDto(val blacklistedApps: List<String>, val severity: Severity)

data class DebugConfigurationDto(val severity: Severity)

data class SafeToRunConfigurationDto(
    val blacklistedAppCheck: BlacklistedAppConfigurationDto,
    val debugConfiguration: DebugConfigurationDto
) {
    companion object {
        fun empty() = SafeToRunConfigurationDto(
            BlacklistedAppConfigurationDto(emptyList(), Severity.None),
            DebugConfigurationDto(Severity.None)
        )
    }
}
