package io.github.dllewellyn.safetorun.backend.services

import io.github.dllewellyn.safetorun.backend.builder.SafeToRunAbstractFactory
import io.github.dllewellyn.safetorun.backend.generators.JwtGenerator
import io.github.dllewellyn.safetorun.models.models.DeviceInformationDto
import io.github.dllewellyn.safetorun.models.models.SafeToRunResult
import io.github.dllewellyn.safetorun.reporting.toGrouped
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class SafeToRunService(
    private val safeToRunTokenGenerator: JwtGenerator,
    private val safeToRunAbstractFactory: SafeToRunAbstractFactory
) {

    fun generateResponseTokenForRequest(deviceInformationDto: DeviceInformationDto): String {
        return safeToRunAbstractFactory.generateSafeToRun(deviceInformationDto).isSafeToRun()
            .toGrouped()
            .also {
                if (it.failedReports.isNotEmpty()) {
                    Log.debug("Failure reporting for ${deviceInformationDto.deviceId}")
                    it.failedReports.forEach { failedReport -> Log.debug(failedReport.failureMessage) }
                }
            }
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

    companion object {
        private val Log = LoggerFactory.getLogger(SafeToRunService::class.java)
    }
}
