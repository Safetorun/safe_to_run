package io.github.dllewellyn.safetorun.backend

import io.github.dllewellyn.safetorun.SafeToRunConfiguration
import io.github.dllewellyn.safetorun.SafeToRunLogic
import io.github.dllewellyn.safetorun.backend.features.blacklistedapps.blacklistedAppCheck
import io.github.dllewellyn.safetorun.backend.features.installorigin.installOriginCheckWithDefaults
import io.github.dllewellyn.safetorun.backend.features.oscheck.osDetectionCheck
import io.github.dllewellyn.safetorun.backend.features.oscheck.osInformation
import io.github.dllewellyn.safetorun.conditional.conditionalBuilder
import io.github.dllewellyn.safetorun.configure
import io.github.dllewellyn.safetorun.features.oscheck.minOsVersion
import io.github.dllewellyn.safetorun.features.oscheck.notManufacturer
import io.github.dllewellyn.safetorun.models.models.DeviceInformationDto
import io.micronaut.context.BeanContext

const val MIN_OS_VERSION = 29

internal fun DeviceInformationDto.safeToRunConfiguration(context: BeanContext): SafeToRunConfiguration =
    configure {
        blacklistedAppCheck(context) {
            +"com.example.abc"
        }.warn()

        osDetectionCheck(
            context,
            with(osInformation()) {
                conditionalBuilder {
                    with(minOsVersion(MIN_OS_VERSION))
                    and(notManufacturer("Abc"))
                }
            }
        ).error()

        installOriginCheckWithDefaults(context).warn()
    }

internal fun DeviceInformationDto.safeToRun(context: BeanContext): SafeToRunLogic =
    SafeToRunOffDevice(safeToRunConfiguration(context))
