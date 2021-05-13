package io.github.dllewellyn.safetorun.backend.features.oscheck

import io.github.dllewellyn.safetorun.backend.models.DeviceInformationDto
import io.github.dllewellyn.safetorun.backend.models.OsCheckDto
import io.github.dllewellyn.safetorun.features.oscheck.OSInformationQuery

class OSInformationQueryDeviceAdapter(private val osCheck: OsCheckDto) : OSInformationQuery {

    override fun osVersion(): Int {
        return osCheck.osVersion.toInt()
    }

    override fun manufacturer(): String {
        return osCheck.manufacturer
    }

    override fun model(): String {
        return osCheck.model
    }
}

fun DeviceInformationDto.osInformation() = OSInformationQueryDeviceAdapter(osCheck)
