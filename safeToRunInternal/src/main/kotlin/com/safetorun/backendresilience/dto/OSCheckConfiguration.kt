package com.safetorun.backendresilience.dto

/**
 * Types of check
 */
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
/**
 * Single os check
 */
data class SingleCheck(
    var intValue: Int? = null,
    var stringValue: String? = null,
    var checkType: CheckType = CheckType.BannedBoard,
    var checkUuid: String = ""
)

/**
 * Os check configuration
 */
@kotlinx.serialization.Serializable
data class OSCheckConfiguration(var configuration: List<SingleOSCheckConfiguration> = emptyList())

/**
 * Convert a list of single os checks to a configuration
 */
fun List<SingleOSCheckConfiguration>.toCheckConfiguration() = OSCheckConfiguration(this)

/**
 * OS Check configuration
 */
@kotlinx.serialization.Serializable
data class SingleOSCheckConfiguration(
    var allChecks: List<SingleCheck> = emptyList(),
    var severity: Severity = Severity.None,
)

/**
 * Build a single os check
 */
@kotlinx.serialization.Serializable
class SingleOSCheckBuilder internal constructor() {
    var allChecks: List<SingleCheck> = emptyList()
    var severity: Severity = Severity.None
    var osConfigurationName: String = ""

    internal fun build() = SingleOSCheckConfiguration(allChecks = allChecks, severity = severity)
}

/**
 * Build an os check configuration
 */
class OSCheckConfigurationBuilder internal constructor() {
    private val osCheckList = mutableListOf<SingleOSCheckConfiguration>()

    /**
     * add a single check
     *
     * @param bloc os check
     */
    fun add(bloc: (SingleOSCheckBuilder.() -> Unit)) {
        osCheckList.add(
            SingleOSCheckBuilder()
                .apply(bloc)
                .build()
        )
    }

    internal fun build(): OSCheckConfiguration {
        return OSCheckConfiguration(osCheckList)
    }
}
