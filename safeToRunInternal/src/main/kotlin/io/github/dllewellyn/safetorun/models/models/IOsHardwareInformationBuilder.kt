package io.github.dllewellyn.safetorun.models.models

interface IOsHardwareInformationBuilder {
    fun hardware(hardware: String): IOsHardwareInformationBuilder
    fun cpuAbi(cpuAbi: String): IOsHardwareInformationBuilder
    fun board(board: String): IOsHardwareInformationBuilder
    fun buildHardwareInformation(): OsHardwareInformation
    fun buildPartialHardwareInformation(): OsHardwareInformation
}
