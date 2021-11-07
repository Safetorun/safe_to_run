package io.github.dllewellyn.safetorun.models.models

/**
 * OS Hardware information object
 *
 * @param cpuAbis the list of CPU ABIs for the device
 * @param board the board for the device
 * @param hardware the hardware for the device
 */
data class OsHardwareInformation(val cpuAbis: List<String>, val board: String, val hardware: String)
