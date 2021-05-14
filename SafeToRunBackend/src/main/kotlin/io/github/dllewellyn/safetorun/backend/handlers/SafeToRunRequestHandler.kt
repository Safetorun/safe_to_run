package io.github.dllewellyn.safetorun.backend.handlers

import io.github.dllewellyn.safetorun.models.models.DeviceInformationDto
import io.github.dllewellyn.safetorun.models.models.DeviceSignatureDto
import io.github.dllewellyn.safetorun.backend.services.SafeToRunService
import io.micronaut.context.annotation.Prototype
import io.micronaut.core.annotation.Introspected
import io.micronaut.function.aws.MicronautRequestHandler
import javax.inject.Inject

@Introspected
@Prototype
class SafeToRunRequestHandler :
    MicronautRequestHandler<DeviceInformationDto, DeviceSignatureDto>() {

    @Inject
    lateinit var safeToRun: SafeToRunService

    override fun execute(input: DeviceInformationDto): DeviceSignatureDto {
        return DeviceSignatureDto(safeToRun.generateResponseTokenForRequest(input))
    }
}
