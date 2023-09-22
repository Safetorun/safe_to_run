package com.safetorun.features.oscheck

import android.os.Build

/**
 * Configure a min os version to fail if we fall below that version
 *
 * @param minOSVersion minimum os version
 */
inline fun minOsVersionCheck(minOSVersion: Int) =
    minOSVersion > Build.VERSION.SDK_INT

/**
 * Configure to disallow a manufacturer - i.e. fail if it's this
 * manufacturer specified
 *
 * @param manufacturerName name of the manufacturer
 */
inline fun notManufacturerCheck(manufacturerName: String) =
    Build.MANUFACTURER == manufacturerName

/**
 * Ban a model from running - i.e. if you add a model that
 * model will not run if that is the model
 *
 * @param bannedModel the model to ban
 */
inline fun bannedModelCheck(bannedModel: String) =
    Build.MODEL == bannedModel

/**
 * Add a banned board to the list
 *
 * @param bannedBoard the model to ban
 */
inline fun bannedBoardCheck(bannedBoard: String) =
    Build.BOARD == bannedBoard

/**
 * Add a banned cpuAbis
 *
 * @param cpuAbi the model to ban
 */
inline fun bannedCpusCheck(cpuAbi: String) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        Build.SUPPORTED_ABIS.contains(cpuAbi)
    } else {
        false
    }

/**
 * Add a banned bootloader to the list
 *
 * @param bannedBootloader the model to ban
 */
inline fun bannedBootloaderCheck(bannedBootloader: String) =
    Build.BOOTLOADER == bannedBootloader

/**
 * Add a banned device
 *
 * @param device device to ban
 */
inline fun bannedDeviceCheck(device: String) =
    Build.DEVICE == device

/**
 * Add a banned host
 *
 * @param hardware hardware to ban
 */
inline fun bannedHardwareCheck(hardware: String) =
    Build.HARDWARE == hardware

/**
 * Add a banned host
 *
 * @param host the model to ban
 */
inline fun bannedHostCheck(host: String) =
    Build.HOST == host
