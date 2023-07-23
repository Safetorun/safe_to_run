package com.safetorun.inline

import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.workDataOf
import com.safetorun.logger.loggerForCheck
import com.safetorun.logger.loggerForVerify
import com.safetorun.reporting.LoggerBackendSynchroniser

/**
 * Safe to run check (Return true if the check fails)
 */
typealias SafeToRunCheck = () -> Boolean


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
 * Safe to run logger for verify
 */
fun <T> Context.verifyLogger(
    apiKey: String,
    checkName: String,
): (Boolean, T?) -> Unit = { value, extraData ->
    loggerForVerify<T>(checkName).invoke(value, extraData)
    startLoggerTracking(apiKey)
}

/**
 * Safe to run logger for checker
 */
fun Context.logger(apiKey: String, checkName: String): (Boolean) -> Unit = {
    loggerForCheck(checkName).invoke(it)
    startLoggerTracking(apiKey)
}

/**
 * Configure
 *
 * @param safeToRunChecks list of checks for warnings
 */
inline fun safeToRunWithLogger(
    crossinline logger: (Boolean) -> Unit,
    vararg safeToRunChecks: SafeToRunCheck
): SafeToRunCheck = safeToRun(logger, safeToRunChecks.toList())


/**
 * Configure
 *
 * @param safeToRunChecks list of checks for warnings
 */
inline fun safeToRun(
    crossinline logger: (Boolean) -> Unit,
    safeToRunChecks: List<SafeToRunCheck>
): SafeToRunCheck = {
    safeToRun(safeToRunChecks).invoke()
        .also { logger(it) }
}

/**
 * Configure
 *
 * @param safeToRunChecks list of checks for warnings
 */
inline fun safeToRun(
    safeToRunChecks: List<SafeToRunCheck>
): SafeToRunCheck {
    return {
        safeToRunChecks
            .map { safeToRunCheck -> safeToRunCheck.invoke() }
            .any { failed -> failed }
    }
}

/**
 * Configure
 *
 * @param safeToRunChecks list of checks for warnings
 */
inline fun safeToRun(
    vararg safeToRunChecks: SafeToRunCheck
): SafeToRunCheck {
    return safeToRun(safeToRunChecks.toList())
}

/**
 * Build a safe to run check list
 * Example:
 * ```
 * safeToRun(buildSafeToRunCheckList {
 *     add {
 *       banAvdEmulatorCheck()
 *     }
 * }
 * ```
 *
 * @param builder a list builder. Add all checks you want to the list
 */
inline fun buildSafeToRunCheckList(builder: MutableList<SafeToRunCheck>.() -> Unit): List<SafeToRunCheck> =
    mutableListOf<SafeToRunCheck>().apply {
        builder()
    }
