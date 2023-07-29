package com.safetorun.plus

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.workDataOf
import com.safetorun.logger.loggerForCheck
import com.safetorun.logger.loggerForVerify

internal fun Context.startLoggerTracking(apiKey: String) {
    val inputData = workDataOf(LoggerBackendSynchroniser.KEY_API_KEY to apiKey)

    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresBatteryNotLow(true)
        .build()


    val uploadWorkRequest: WorkRequest =
        OneTimeWorkRequestBuilder<LoggerBackendSynchroniser>()
            .setConstraints(constraints)
            .setInputData(inputData)
            .build()

    WorkManager
        .getInstance(this)
        .enqueue(uploadWorkRequest)
}


/**
 * Safe to run logger for verify.
 *
 * Note: Be sure you call [initialiseSafeToRunPlus] before using this method
 */
fun <T> Context.verifyLogger(
    checkName: String
): (Boolean, T?) -> Unit = { value, extraData ->
    loggerForVerify<T>(checkName).invoke(value, extraData)
    startLoggerTracking(SafeToRunPlus.apiKey)
}

/**
 * Safe to run logger for verify.
 *
 * Note: Be sure you call [initialiseSafeToRunPlus] before using this method
 */
fun Context.logger(checkName: String): (Boolean) -> Unit = {
    loggerForCheck(checkName).invoke(it)
    startLoggerTracking(SafeToRunPlus.apiKey)
}

/**
 * Initialise safe to run plus
 *
 * @param apiKey api key for the backend
 */
fun initialiseSafeToRunPlus(apiKey: String) {
    SafeToRunPlus.initialise(apiKey)
}

@SuppressLint("StaticFieldLeak")
private object SafeToRunPlus {

    private lateinit var _apiKey: String
    val apiKey get() = _apiKey

    fun initialise(apiKey: String) {
        this._apiKey = apiKey
    }
}
