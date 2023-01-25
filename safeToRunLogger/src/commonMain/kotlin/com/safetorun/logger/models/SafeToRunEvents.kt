package com.safetorun.logger.models

import java.util.*

@kotlinx.serialization.Serializable
internal sealed class SafeToRunEvents(
    val time: Long = Calendar.getInstance().time.time,
    val uuid: String = UUID.randomUUID().toString(),
    val metadata: AppMetadata
) {

    @kotlinx.serialization.Serializable
    data class FailedCheck(
        val deviceInformation: DeviceInformation,
        val appMetadata: AppMetadata,
        val checkName: String
    ) :
        SafeToRunEvents(metadata = appMetadata)

    @kotlinx.serialization.Serializable
    data class SucceedCheck(
        val deviceInformation: DeviceInformation,
        val appMetadata: AppMetadata,
        val checkName: String
    ) :
        SafeToRunEvents(metadata = appMetadata)
}
