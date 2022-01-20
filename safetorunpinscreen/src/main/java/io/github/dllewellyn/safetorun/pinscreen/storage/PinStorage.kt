package io.github.dllewellyn.safetorun.pinscreen.storage

internal interface PinStorage {
    suspend fun clear()
    suspend fun savePin(pin : String)
    suspend fun retrievePin() : String?
    suspend fun retrieveOrCreateSalt() : String
}
