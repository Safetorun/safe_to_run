package com.safetorun.models.builders

import com.safetorun.models.models.OsHardwareInformationDto

/**
 * OS Hardware information builder. Use to generate a [OsHardwareInformationDto]
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
    fun buildHardwareInformation(): OsHardwareInformationDto

    /**
     * Build partial hardware information. Uses empty strings
     * if all required params are not provied
     */
    fun buildPartialHardwareInformation(): OsHardwareInformationDto
}
