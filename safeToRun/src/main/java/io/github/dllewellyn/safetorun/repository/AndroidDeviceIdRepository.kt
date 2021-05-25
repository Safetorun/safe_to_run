package io.github.dllewellyn.safetorun.repository

import android.content.Context
import android.content.SharedPreferences
import java.util.UUID
import java.util.concurrent.Executors

internal class AndroidDeviceIdRepository(private val context: Context) : DeviceIdRepository {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
    }

    private val executor by lazy { Executors.newSingleThreadExecutor() }

    override fun getOrCreateDeviceIdSync(): String =
        if (!sharedPreferences.contains(DEVICE_ID)) {
            UUID.randomUUID().toString().apply {
                sharedPreferences.edit().putString(DEVICE_ID, this).apply()
            }
        } else {
            requireNotNull(sharedPreferences.getString(DEVICE_ID, ""))
        }

    override fun getOrCreateDeviceIdAsync(deviceId: (String) -> Unit) {
        executor.execute {
            deviceId(getOrCreateDeviceIdSync())
        }
    }

    companion object {
        const val SHARED_PREFS = "safe_to_run"
        const val DEVICE_ID = "device_id"
    }
}
