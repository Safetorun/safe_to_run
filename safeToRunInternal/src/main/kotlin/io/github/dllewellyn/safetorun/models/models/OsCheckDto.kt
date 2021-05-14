package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable

@Serializable
class OsCheckDto {
    var osVersion: String = ""
    var manufacturer: String = ""
    var model: String = ""
}
