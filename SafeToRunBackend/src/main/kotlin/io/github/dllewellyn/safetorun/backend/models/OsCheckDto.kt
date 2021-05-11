package io.github.dllewellyn.safetorun.backend.models

import io.micronaut.core.annotation.Introspected

@Introspected
class OsCheckDto {
    var osVersion: String = ""
    var manufacturer: String = ""
}