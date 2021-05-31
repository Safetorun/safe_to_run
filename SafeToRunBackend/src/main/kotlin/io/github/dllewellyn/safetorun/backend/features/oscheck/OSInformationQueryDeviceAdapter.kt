package io.github.dllewellyn.safetorun.backend.features.oscheck

import io.github.dllewellyn.safetorun.models.models.DeviceInformationDto
import io.github.dllewellyn.safetorun.models.models.OsCheckDto
import io.github.dllewellyn.safetorun.features.oscheck.OSInformationQuery

internal class OSInformationQueryDeviceAdapter(private val osCheck: OsCheckDto) : OSInformationQuery {

    override fun osVersion(): Int {
        return osCheck.osVersion.toInt()
    }

    override fun manufacturer(): String {
        return osCheck.manufacturer
    }

    override fun model(): String {
        return osCheck.model
    }

    override fun board(): String {
        return osCheck.board
    }

    override fun bootloader(): String {
        return osCheck.bootloader
    }

    override fun cpuAbi(): List<String> {
        return osCheck.cpuAbi
    }

    override fun host(): String {
        return osCheck.host
    }

    override fun hardware(): String {
        return osCheck.hardware
    }

    override fun device(): String {
        return osCheck.device
    }
}

internal fun DeviceInformationDto.osInformation() = OSInformationQueryDeviceAdapter(osCheck)
