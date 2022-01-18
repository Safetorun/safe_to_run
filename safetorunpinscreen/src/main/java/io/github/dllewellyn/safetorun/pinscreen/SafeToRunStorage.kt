package io.github.dllewellyn.safetorun.pinscreen

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import io.github.dllewellyn.safetorun.pinscreen.models.Attempts
import io.github.dllewellyn.safetorun.pinscreen.storage.AttemptsLogger
import io.github.dllewellyn.safetorun.pinscreen.storage.PinStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

internal class SafeToRunStorage(private val context: Context) : PinStorage,
    AttemptsLogger {

    private val masterKeyAlias by lazy { MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC) }

    private val sharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            "pin_screen",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override suspend fun savePin(pin: String) {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit()
                .putString(PIN_STORAGE, pin)
                .apply()
        }
    }

    override suspend fun retrievePin(): String? =
        withContext(Dispatchers.IO) {
            sharedPreferences.getString(PIN_STORAGE, null)
        }

    override suspend fun logAttempt(attempts: Attempts) {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit()
                .putInt(ATTEMPTS, attempts.attempts)
                .putLong(LAST_ATTEMPT_TIME, attempts.lastAttemptTime.time)
                .apply()
        }
    }

    override suspend fun getAttempts(): Attempts? =
        withContext(Dispatchers.IO) {
            val lastAttemptTime = sharedPreferences.getLong(LAST_ATTEMPT_TIME, -1)
            val attempts = sharedPreferences.getInt(ATTEMPTS, -1)

            if (lastAttemptTime > 0 && attempts > 0) {
                Attempts(attempts, Calendar.getInstance().run {
                    timeInMillis = lastAttemptTime
                    time
                })
            } else {
                null
            }
        }

    suspend fun clear() = withContext(Dispatchers.IO) {
        sharedPreferences.edit()
            .clear()
            .apply()
    }

    companion object {
        const val PIN_STORAGE = "pin"
        const val LAST_ATTEMPT_TIME = "last_attempts"
        const val ATTEMPTS = "attempts_number"
    }

}


internal fun safeToRunPinStorage(context: Context) = SafeToRunStorage(context)
