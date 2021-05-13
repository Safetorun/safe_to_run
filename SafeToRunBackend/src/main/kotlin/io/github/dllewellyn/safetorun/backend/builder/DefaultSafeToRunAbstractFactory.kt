package io.github.dllewellyn.safetorun.backend.builder

import io.github.dllewellyn.safetorun.backend.models.DeviceInformationDto
import io.github.dllewellyn.safetorun.backend.safeToRun
import io.micronaut.context.BeanContext
import javax.inject.Singleton

@Singleton
class DefaultSafeToRunAbstractFactory(private val beanContext: BeanContext) : SafeToRunAbstractFactory {
    override fun generateSafeToRun(deviceInformation: DeviceInformationDto) = deviceInformation.safeToRun(beanContext)
}