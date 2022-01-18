package io.github.dllewellyn.safetorun.pinscreen.storage

internal interface PinStorage {
    suspend fun savePin(pin : String)
    suspend fun retrievePin() : String?
}
