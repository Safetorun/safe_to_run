package com.safetorun.logger.models

import kotlinx.serialization.SerialName
import java.util.*

/**
 * Fire events to safe to run backend
 */
@kotlinx.serialization.Serializable
sealed class SafeToRunEvents(
    val time: Long = Calendar.getInstance().time.time,
    val uuid: String = UUID.randomUUID().toString(),
    val metadata: AppMetadata
) {

    /**
     * A check failed
     */
    @kotlinx.serialization.Serializable
    @SerialName("Failed")
    data class FailedCheck(
        val deviceInformation: DeviceInformation,
        val appMetadata: AppMetadata,
        val checkName: String
    ) :
        SafeToRunEvents(metadata = appMetadata)

    /**
     * A check succeeded
     */
    @kotlinx.serialization.Serializable
    @SerialName("Succeed")
    data class SucceedCheck(
        val deviceInformation: DeviceInformation,
        val appMetadata: AppMetadata,
        val checkName: String
    ) :
        SafeToRunEvents(metadata = appMetadata)
}
