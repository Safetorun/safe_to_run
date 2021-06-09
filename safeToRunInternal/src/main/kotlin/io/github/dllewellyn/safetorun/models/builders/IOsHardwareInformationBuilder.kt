package io.github.dllewellyn.safetorun.models.builders

import io.github.dllewellyn.safetorun.models.models.OsHardwareInformation

/**
 * OS Hardware information builder. Use to generate a [OsHardwareInformation]
 * instance
 */
interface IOsHardwareInformationBuilder {

    /**
     * Hardware information
     *
     * @param hardware hardware information
     */
    fun hardware(hardware: String): IOsHardwareInformationBuilder

    /**
     * Cpu ABI
     *
     * @param cpuAbi cpu ABI information
     */
    fun cpuAbi(cpuAbi: String): IOsHardwareInformationBuilder

    /**
     * Board
     *
     * @param board board information
     */
    fun board(board: String): IOsHardwareInformationBuilder

    /**
     * Build the model for hardware information. Throws exception
     * if all required params are not provided
     */
    fun buildHardwareInformation(): OsHardwareInformation

    /**
     * Build partial hardware information. Uses empty strings
     * if all required params are not provied
     */
    fun buildPartialHardwareInformation(): OsHardwareInformation
}