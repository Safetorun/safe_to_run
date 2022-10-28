package com.safetorun.backendresilience.dto

enum class CheckType {
    MinOsCheck,
    BannedBoard,
    BannedBootloader,
    BannedCpuAbi,
    BannedDevice,
    BannedHardware,
    BannedHost,
    BannedModel
}

@kotlinx.serialization.Serializable
data class SingleCheck(
    var intValue: Int? = null,
    var stringValue: String? = null,
    var checkType: CheckType = CheckType.BannedBoard,
    var checkUuid: String = ""
)

@kotlinx.serialization.Serializable
data class OSCheckConfiguration(var configuration: List<SingleOSCheckConfiguration> = emptyList())

fun List<SingleOSCheckConfiguration>.toCheckConfiguration() = OSCheckConfiguration(this)

/**
 * OS Check configuration
 */
@kotlinx.serialization.Serializable
data class SingleOSCheckConfiguration(
    var allChecks: List<SingleCheck> = emptyList(),
    var severity: Severity = Severity.None,
)

@kotlinx.serialization.Serializable
class SingleOSCheckBuilder internal constructor() {
    var allChecks: List<SingleCheck> = emptyList()
    var severity: Severity = Severity.None
    var osConfigurationName: String = ""

    internal fun build() = SingleOSCheckConfiguration(severity = severity)
}

class OSCheckConfigurationBuilder internal constructor() {
    private val allowedInstallOriginCheck = mutableListOf<SingleOSCheckConfiguration>()

    fun add(bloc: (SingleOSCheckBuilder.() -> Unit)) {
        allowedInstallOriginCheck.add(
            SingleOSCheckBuilder()
                .apply(bloc)
                .build()
        )
    }

    internal fun build(): OSCheckConfiguration {
        return OSCheckConfiguration(allowedInstallOriginCheck)
    }
}
