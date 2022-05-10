package com.safetorun.exploration

/**
 * Results of OS Check
 */
data class OsCheck(
    val osVersion: String,
    val manufacturer: String,
    val model: String,
    val board: String,
    val bootloader: String,
    val cpuAbi: List<String>,
    val host: String,
    val hardware: String,
    val device: String
)
