package io.github.dllewellyn.safetorun.backend.builder

import io.github.dllewellyn.safetorun.SafeToRunLogic
import io.github.dllewellyn.safetorun.models.models.DeviceInformationDto

interface SafeToRunAbstractFactory {
    fun generateSafeToRun(deviceInformation: DeviceInformationDto): SafeToRunLogic
}
