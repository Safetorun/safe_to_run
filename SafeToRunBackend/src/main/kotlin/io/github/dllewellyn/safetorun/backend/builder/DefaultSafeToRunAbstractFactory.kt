package io.github.dllewellyn.safetorun.backend.builder

import io.github.dllewellyn.safetorun.SafeToRun
import io.github.dllewellyn.safetorun.backend.models.DeviceInformationDto
import javax.inject.Singleton

@Singleton
class DefaultSafeToRunAbstractFactory(private val safeToRun: SafeToRun) : SafeToRunAbstractFactory {
    override fun generateSafeToRun(deviceInformation: DeviceInformationDto) = safeToRun
}