package com.safetorun.features.oscheck

import android.os.Build
import com.safetorun.plus.queries.osInformationQuery

/**
 * Configure a min os version to fail if we fall below that version
 *
 * @param minOSVersion minimum os version
 */
inline fun minOsVersionCheck(
    minOSVersion: Int,
    osCheck: OSInformationQuery = osInformationQuery()
) =
    minOSVersion > osCheck.osVersion()

/**
 * Configure to disallow a manufacturer - i.e. fail if it's this
 * manufacturer specified
 *
 * @param manufacturerName name of the manufacturer
 */
inline fun notManufacturerCheck(
    manufacturerName: String,
    osCheck: OSInformationQuery = osInformationQuery()
) =
    osCheck.manufacturer() == manufacturerName

/**
 * Ban a model from running - i.e. if you add a model that
 * model will not run if that is the model
 *
 * @param bannedModel the model to ban
 */
inline fun bannedModelCheck(
    bannedModel: String,
    osCheck: OSInformationQuery = osInformationQuery()
) =
    osCheck.model() == bannedModel

/**
 * Add a banned board to the list
 *
 * @param bannedBoard the model to ban
 */
inline fun bannedBoardCheck(
    bannedBoard: String,
    osCheck: OSInformationQuery = osInformationQuery()
) =
    osCheck.board() == bannedBoard

/**
 * Add a banned cpuAbis
 *
 * @param cpuAbi the model to ban
 */
inline fun bannedCpusCheck(cpuAbi: String, osCheck: OSInformationQuery = osInformationQuery()) =
    osCheck.cpuAbi().contains(cpuAbi)

/**
 * Add a banned bootloader to the list
 *
 * @param bannedBootloader the model to ban
 */
inline fun bannedBootloaderCheck(
    bannedBootloader: String,
    osCheck: OSInformationQuery = osInformationQuery()
) =
    osCheck.bootloader() == bannedBootloader

/**
 * Add a banned device
 *
 * @param device device to ban
 */
inline fun bannedDeviceCheck(device: String, osCheck: OSInformationQuery = osInformationQuery()) =
    osCheck.device() == device

/**
 * Add a banned host
 *
 * @param hardware hardware to ban
 */
inline fun bannedHardwareCheck(
    hardware: String,
    osCheck: OSInformationQuery = osInformationQuery()
) =
    osCheck.hardware() == hardware

/**
 * Add a banned host
 *
 * @param host the model to ban
 */
inline fun bannedHostCheck(host: String, osCheck: OSInformationQuery = osInformationQuery()) =
    osCheck.host() == host
