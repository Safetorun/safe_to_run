package com.safetorun.models.builders

import com.safetorun.models.logger.OsHardwareInformation

internal class OsHardwareInformationBuilder : IOsHardwareInformationBuilder {
    private val _cpuAbis = mutableListOf<String>()
    private var _board: String? = null
    private var _hardware: String? = null

    /**
     * Add hardware
     */
    override fun hardware(hardware: String): IOsHardwareInformationBuilder {
        this._hardware = hardware
        return this
    }

    /**
     * Add CPU abi
     */
    override fun cpuAbi(cpuAbi: String): IOsHardwareInformationBuilder {
        this._cpuAbis.add(cpuAbi)
        return this
    }

    override fun board(board: String): IOsHardwareInformationBuilder {
        this._board = board
        return this
    }

   override fun buildHardwareInformation(): OsHardwareInformation {
       val board: String = _board ?: ""
       val hardware: String = _hardware ?: ""
        return OsHardwareInformation(_cpuAbis, board, hardware)
    }
}
