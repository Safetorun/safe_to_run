package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable

@Serializable
class OsCheckDto {
    var osVersion: String = ""
    var manufacturer: String = ""
    var model: String = ""

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OsCheckDto

        if (osVersion != other.osVersion) return false
        if (manufacturer != other.manufacturer) return false
        if (model != other.model) return false

        return true
    }

    override fun hashCode(): Int {
        var result = osVersion.hashCode()
        result = 31 * result + manufacturer.hashCode()
        result = 31 * result + model.hashCode()
        return result
    }

    override fun toString(): String {
        return "OsCheckDto(osVersion='$osVersion', manufacturer='$manufacturer', model='$model')"
    }
}
