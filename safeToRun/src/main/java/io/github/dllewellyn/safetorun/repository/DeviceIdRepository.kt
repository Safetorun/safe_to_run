package io.github.dllewellyn.safetorun.repository

internal interface DeviceIdRepository {
    fun getOrCreateDeviceIdSync(): String
    fun getOrCreateDeviceIdAsync(deviceId: (String) -> Unit)
}
