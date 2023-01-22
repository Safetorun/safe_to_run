package com.safetorun.models.logger

import java.util.Calendar
import java.util.UUID

@kotlinx.serialization.Serializable
internal sealed class SafeToRunEvents(
    val time: Long = Calendar.getInstance().time.time,
    val uuid: String = UUID.randomUUID().toString()
) {

    @kotlinx.serialization.Serializable
    data class FailedCheck(val deviceInformation: DeviceInformation, val checkName: String) :
        SafeToRunEvents()

    @kotlinx.serialization.Serializable
    data class SucceedCheck(val deviceInformation: DeviceInformation, val checkName: String) :
        SafeToRunEvents()
}
