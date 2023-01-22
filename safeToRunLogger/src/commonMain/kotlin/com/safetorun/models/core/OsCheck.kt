package com.safetorun.models.core


/**
 * DTO Containing OS information like version, model, board etc
 */
@kotlinx.serialization.Serializable
data class OsCheck(
    val osVersion: String,
    val manufacturer: String,
    val model: String,
    val board: String,
    val bootloader: String,
    val cpuAbi: List<String>,
    val host: String,
    val hardware: String,
    val device: String,
)
