package io.github.dllewellyn.safetorun.backend.builder

import io.github.dllewellyn.safetorun.SafeToRun
import io.github.dllewellyn.safetorun.backend.models.DeviceInformationDto

interface SafeToRunAbstractFactory {
    fun generateSafeToRun(deviceInformation: DeviceInformationDto): SafeToRun
}
