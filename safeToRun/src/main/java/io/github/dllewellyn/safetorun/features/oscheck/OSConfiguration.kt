package io.github.dllewellyn.safetorun.features.oscheck

import io.github.dllewellyn.safetorun.conditional.Conditional
import io.github.dllewellyn.safetorun.features.oscheck.builders.bannedBoard
import io.github.dllewellyn.safetorun.features.oscheck.builders.bannedBootloader
import io.github.dllewellyn.safetorun.features.oscheck.builders.bannedCpus
import io.github.dllewellyn.safetorun.features.oscheck.builders.bannedDevice
import io.github.dllewellyn.safetorun.features.oscheck.builders.bannedHardware
import io.github.dllewellyn.safetorun.features.oscheck.builders.bannedHost
import io.github.dllewellyn.safetorun.features.oscheck.builders.bannedModel
import io.github.dllewellyn.safetorun.features.oscheck.builders.minOsVersion
import io.github.dllewellyn.safetorun.features.oscheck.conditionals.notManufacturer
import io.github.dllewellyn.safetorun.features.oscheck.emulators.banAvdEmulator

/**
 * Used to wrap an os configuration with the default OS Information query for the application
 */
object OSConfiguration : OSInformationQuery by OSInformationQueryAndroid()

/**
 * Configure a min os version to fail if we fall below that version
 *
 * @param minOSVersion minimum os version
 */
fun minOsVersion(minOSVersion: Int): Conditional {
    return OSConfiguration.minOsVersion(minOSVersion)
}

/**
 * Configure to disallow a manufacturer - i.e. fail if it's this
 * manufacturer specified
 *
 * @param manufacturerName name of the manufacturer
 */
fun notManufacturer(manufacturerName: String): Conditional {
    return OSConfiguration.notManufacturer(manufacturerName)
}

/**
 * Ban a model from running - i.e. if you add a model that
 * model will not run if that is the model
 *
 * @param bannedModel the model to ban
 */
fun bannedModel(bannedModel: String): Conditional {
    return OSConfiguration.bannedModel(bannedModel)
}

/**
 * Add a banned board to the list
 *
 * @param bannedBoard the model to ban
 */
fun bannedBoard(bannedBoard: String): Conditional =
    OSConfiguration.bannedBoard(bannedBoard)

/**
 * Add a banned bootloader to the list
 *
 * @param bannedBootloader the model to ban
 */
fun bannedBootloader(bannedBootloader: String): Conditional =
    OSConfiguration.bannedBootloader(bannedBootloader)

/**
 * Add a banned cpuAbis
 *
 * @param cpuAbis the model to ban
 */
fun bannedCpus(cpuAbis: String): Conditional =
    OSConfiguration.bannedCpus(cpuAbis)

/**
 * Add a banned device
 *
 * @param device device to ban
 */
fun bannedDevice(device: String): Conditional =
    OSConfiguration.bannedDevice(device)

/**
 * Add a banned host
 *
 * @param hardware hardware to ban
 */
fun bannedHardware(hardware: String): Conditional =
    OSConfiguration.bannedHardware(hardware)

/**
 * Add a banned host
 *
 * @param host the model to ban
 */
fun bannedHost(host: String): Conditional =
    OSConfiguration.bannedHost(host)

/**
 * Ban the default AVD emulator from running
 */
fun banAvdEmulator(): Conditional =
    OSConfiguration.banAvdEmulator()
