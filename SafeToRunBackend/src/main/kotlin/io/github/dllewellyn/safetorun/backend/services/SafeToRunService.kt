package io.github.dllewellyn.safetorun.backend.services

import io.github.dllewellyn.safetorun.backend.builder.SafeToRunAbstractFactory
import io.github.dllewellyn.safetorun.backend.generators.JwtGenerator
import io.github.dllewellyn.safetorun.backend.models.DeviceInformationDto
import io.github.dllewellyn.safetorun.backend.models.SafeToRunResult
import io.github.dllewellyn.safetorun.reporting.toGrouped
import javax.inject.Singleton

@Singleton
class SafeToRunService(
    private val safeToRunTokenGenerator: JwtGenerator,
    private val safeToRunAbstractFactory: SafeToRunAbstractFactory
) {

    fun generateResponseTokenForRequest(deviceInformationDto: DeviceInformationDto): String {
        return safeToRunAbstractFactory.generateSafeToRun(deviceInformationDto).isSafeToRun()
            .toGrouped()
            .run {
                safeToRunTokenGenerator.generateSecretFor(
                    SafeToRunResult(
                        failures = failedReports.size,
                        successes = successReports.size,
                        warnings = warningReports.size,
                        apiKey = deviceInformationDto.apiKey,
                        deviceId = deviceInformationDto.deviceId
                    )
                )
            }
    }
}
