package io.github.dllewellyn.safetorun.backend

import io.github.dllewellyn.safetorun.SafeToRunConfiguration
import io.github.dllewellyn.safetorun.SafeToRunLogic
import io.github.dllewellyn.safetorun.backend.configuration.Severity
import io.github.dllewellyn.safetorun.backend.features.blacklistedapps.blacklistedAppCheck
import io.github.dllewellyn.safetorun.backend.features.installorigin.installOriginCheckWithDefaults
import io.github.dllewellyn.safetorun.backend.features.installorigin.installOriginCheckWithoutDefaults
import io.github.dllewellyn.safetorun.backend.features.oscheck.osDetectionCheck
import io.github.dllewellyn.safetorun.backend.features.oscheck.osInformation
import io.github.dllewellyn.safetorun.backend.features.signature.verifySignatureCheck
import io.github.dllewellyn.safetorun.backend.repository.SafeToRunConfigurationRepository
import io.github.dllewellyn.safetorun.checks.SafeToRunCheck
import io.github.dllewellyn.safetorun.conditional.conditionalBuilder
import io.github.dllewellyn.safetorun.configure
import io.github.dllewellyn.safetorun.features.oscheck.builders.minOsVersion
import io.github.dllewellyn.safetorun.features.oscheck.conditionals.notManufacturer
import io.github.dllewellyn.safetorun.models.models.DeviceInformationDto
import io.micronaut.context.BeanContext

const val MIN_OS_VERSION = 29

@Suppress("SpreadOperator")
internal fun DeviceInformationDto.safeToRunConfiguration(context: BeanContext): SafeToRunConfiguration =
    with(context.getBean(SafeToRunConfigurationRepository::class.java).retrieveRepositoryForKey(apiKey)) {

        configure {
            addOnSeverity(
                blacklistedAppCheck(context) {
                    blacklistedAppCheck.blacklistedApps.forEach {
                        +it
                    }
                },
                severity = blacklistedAppCheck.severity
            )

            with(osInformation()) {
                osDetectionCheck(
                    context,
                    conditionalBuilder {
                        with(minOsVersion(MIN_OS_VERSION))
                        and(notManufacturer("Abc"))
                    }
                ).error()
            }

            addOnSeverity(
                verifySignatureCheck(context, *verifySignatureConfiguration.allowedSignatures.toTypedArray()),
                severity = verifySignatureConfiguration.severity
            )

            addOnSeverity(
                installOriginCheckWithoutDefaults(context, *installOriginCheck.allowedInstallOrigins.toTypedArray()),
                severity = installOriginCheck.severity
            )
        }
    }


internal fun DeviceInformationDto.safeToRun(context: BeanContext): SafeToRunLogic =
    SafeToRunOffDevice(safeToRunConfiguration(context))

internal fun SafeToRunConfiguration.addOnSeverity(safeToRunCheck: SafeToRunCheck, severity: Severity) {
    when (severity) {
        Severity.Warn -> safeToRunCheck.warn()
        Severity.Error -> safeToRunCheck.error()
        Severity.None -> {
            // NOOP
        }
    }
}
