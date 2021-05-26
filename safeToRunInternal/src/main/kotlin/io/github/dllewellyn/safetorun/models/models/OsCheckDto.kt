package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable

@Serializable
class OsCheckDto {
    var osVersion: String = ""
    var manufacturer: String = ""
    var model: String = ""
    var board: String = ""
    var bootloader: String = ""
    var cpuAbi: List<String> = emptyList()
    var host: String = ""
    var hardware: String = ""
    var device: String = ""

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OsCheckDto

        return (osVersion == other.osVersion) &&
                (manufacturer == other.manufacturer) &&
                (model == other.model) &&
                (board == other.board) &&
                (bootloader == other.bootloader) &&
                (cpuAbi == other.cpuAbi) &&
                (host == other.host) &&
                (hardware == other.hardware) &&
                (device == other.device)
    }

    override fun hashCode(): Int {
        var result = osVersion.hashCode()
        result = hashCodeBuilder(result, manufacturer)
        result = hashCodeBuilder(result, model)
        result = hashCodeBuilder(result, board)
        result = hashCodeBuilder(result, bootloader)
        result = hashCodeBuilder(result, cpuAbi)
        result = hashCodeBuilder(result, host)
        result = hashCodeBuilder(result, hardware)
        result = hashCodeBuilder(result, device)
        return result
    }

    private fun hashCodeBuilder(result: Int, item: Any) =
        HASH_CODE_BASE_VALUE * result + item.hashCode()

    override fun toString(): String {
        return "OsCheckDto(osVersion='$osVersion', " +
                "manufacturer='$manufacturer'," +
                " model='$model', board='$board', " +
                "bootloader='$bootloader'," +
                " cpuAbi=$cpuAbi, " +
                "host='$host'," +
                " hardware='$hardware'," +
                " device='$device')"
    }

    companion object {
        const val HASH_CODE_BASE_VALUE = 31
    }
}
