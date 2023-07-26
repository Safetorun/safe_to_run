package com.safetorun.plus.models

import kotlinx.serialization.Serializable

@Serializable
/**
 * DTO Containing OS information like version, model, board etc
 */
data class OsCheckDto(
    var osVersion: String = "",
    var manufacturer: String = "",
    var model: String = "",
    var board: String = "",
    var bootloader: String = "",
    var cpuAbi: List<String> = emptyList(),
    var host: String = "",
    var hardware: String = "",
    var device: String = "",
)
