package com.safetorun.logger.models

import kotlinx.serialization.SerialName
import java.util.*

@kotlinx.serialization.Serializable
sealed class SafeToRunEvents(
    val time: Long = Calendar.getInstance().time.time,
    val uuid: String = UUID.randomUUID().toString(),
    val metadata: AppMetadata
) {

    @kotlinx.serialization.Serializable
    @SerialName("Failed")
    data class FailedCheck(
        val deviceInformation: DeviceInformation,
        val appMetadata: AppMetadata,
        val checkName: String
    ) :
        SafeToRunEvents(metadata = appMetadata)

    @kotlinx.serialization.Serializable
    @SerialName("Succeed")
    data class SucceedCheck(
        val deviceInformation: DeviceInformation,
        val appMetadata: AppMetadata,
        val checkName: String
    ) :
        SafeToRunEvents(metadata = appMetadata)
}
