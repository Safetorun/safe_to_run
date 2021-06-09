package io.github.dllewellyn.safetorun.backend.services

import io.github.dllewellyn.safetorun.backend.builder.SafeToRunAbstractFactory
import io.github.dllewellyn.safetorun.backend.generators.JwtGenerator
import io.github.dllewellyn.safetorun.models.models.DeviceInformationDto
import io.github.dllewellyn.safetorun.models.models.SafeToRunResult
import io.github.dllewellyn.safetorun.reporting.GroupedSafeToRunReports
import io.github.dllewellyn.safetorun.reporting.toGrouped
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
internal class SafeToRunService(
    private val safeToRunTokenGenerator: JwtGenerator,
    private val safeToRunAbstractFactory: SafeToRunAbstractFactory
) {

    fun generateResponseTokenForRequest(deviceInformationDto: DeviceInformationDto): String {
        return safeToRunAbstractFactory.generateSafeToRun(deviceInformationDto).isSafeToRun()
            .toGrouped()
            .also { logIfThereIsAFailure(it, deviceInformationDto) }
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

    private fun logIfThereIsAFailure(
        groupedReports: GroupedSafeToRunReports,
        deviceInformation: DeviceInformationDto
    ) {
        if (groupedReports.failedReports.isNotEmpty()) {
            buildString {
                append("Failure reporting for ${deviceInformation.deviceId} - ")
                groupedReports.failedReports.forEach { failedReport ->
                    append("${failedReport.failureMessage} and ")
                }
            }.removeSuffix(" and ")
                .let(Log::debug)
        }
    }

    companion object {
        private val Log = LoggerFactory.getLogger(SafeToRunService::class.java)
    }
}