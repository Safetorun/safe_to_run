package com.safetorun.features.oscheck

import io.github.dllewellyn.safetorun.conditional.Conditional
import com.safetorun.features.oscheck.builders.bannedBoard
import com.safetorun.features.oscheck.builders.bannedBootloader
import com.safetorun.features.oscheck.builders.bannedCpus
import com.safetorun.features.oscheck.builders.bannedDevice
import com.safetorun.features.oscheck.builders.bannedHardware
import com.safetorun.features.oscheck.builders.bannedHost
import com.safetorun.features.oscheck.builders.bannedModel
import com.safetorun.features.oscheck.builders.minOsVersion
import com.safetorun.features.oscheck.conditionals.notManufacturer

/**
 * Used to wrap an os configuration with the default OS Information query for the application
 */
object OSConfiguration : OSInformationQuery by OSInformationQueryAndroid()

/**
 * Configure a min os version to fail if we fall below that version
 *
 * @param minOSVersion minimum os version
 */
inline fun minOsVersion(minOSVersion: Int): Conditional {
    return OSConfiguration.minOsVersion(minOSVersion)
}

/**
 * Configure to disallow a manufacturer - i.e. fail if it's this
 * manufacturer specified
 *
 * @param manufacturerName name of the manufacturer
 */
inline fun notManufacturer(manufacturerName: String): Conditional {
    return OSConfiguration.notManufacturer(manufacturerName)
}

/**
 * Ban a model from running - i.e. if you add a model that
 * model will not run if that is the model
 *
 * @param bannedModel the model to ban
 */
inline fun bannedModel(bannedModel: String): Conditional {
    return OSConfiguration.bannedModel(bannedModel)
}

/**
 * Add a banned board to the list
 *
 * @param bannedBoard the model to ban
 */
inline fun bannedBoard(bannedBoard: String): Conditional =
    OSConfiguration.bannedBoard(bannedBoard)

/**
 * Add a banned bootloader to the list
 *
 * @param bannedBootloader the model to ban
 */
inline fun bannedBootloader(bannedBootloader: String): Conditional =
    OSConfiguration.bannedBootloader(bannedBootloader)

/**
 * Add a banned cpuAbis
 *
 * @param cpuAbis the model to ban
 */
inline fun bannedCpus(cpuAbis: String): Conditional =
    OSConfiguration.bannedCpus(cpuAbis)

/**
 * Add a banned device
 *
 * @param device device to ban
 */
inline fun bannedDevice(device: String): Conditional =
    OSConfiguration.bannedDevice(device)

/**
 * Add a banned host
 *
 * @param hardware hardware to ban
 */
inline fun bannedHardware(hardware: String): Conditional =
    OSConfiguration.bannedHardware(hardware)

/**
 * Add a banned host
 *
 * @param host the model to ban
 */
inline fun bannedHost(host: String): Conditional =
    OSConfiguration.bannedHost(host)
