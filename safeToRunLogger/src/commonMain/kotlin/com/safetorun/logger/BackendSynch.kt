package com.safetorun.logger

import android.content.Context
import com.safetorun.logger.metadata.metadata
import com.safetorun.logger.models.DeviceInformation
import com.safetorun.logger.models.SafeToRunEvents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * Retrieve a logger for a given check
 *
 * @param checkName a unique name to describe your check
 */
fun Context.loggerForCheck(
    checkName: String,
    scope: CoroutineScope = GlobalScope
): (Boolean) -> Unit = {
    scope.launch {
        if (it) {
            logCheckSuccess(checkName)
        } else {
            logCheckFailure(checkName)
        }
    }
}

/**
 * Retrieve a logger for a given check
 *
 * @param checkName a unique name to describe your check
 */
fun <T> Context.loggerForVerify(
    checkName: String,
    scope: CoroutineScope = GlobalScope
): (Boolean, T?) -> Unit = { pass, value ->
    scope.launch {
        if (pass) {
            logVerifySuccess(checkName, value?.toString())
        } else {
            logVerifyFailure(checkName, value?.toString())
        }
    }
}

internal suspend fun Context.logCheckFailure(checkName: String) =
    AndroidDataStore(this@logCheckFailure)
        .store(
            SafeToRunEvents.FailedCheck(
                appMetadata = metadata(),
                checkName = checkName,
                deviceInformation = DeviceInformation.empty()
            )
        )

internal suspend fun Context.logVerifyFailure(
    checkName: String,
    extraInfo: String?
) =
    AndroidDataStore(this@logVerifyFailure)
        .store(
            SafeToRunEvents.FailedVerify(
                appMetadata = metadata(),
                checkName = checkName,
                deviceInformation = DeviceInformation.empty(),
                extra = extraInfo
            )
        )

internal suspend fun Context.logVerifySuccess(
    checkName: String,
    extraInfo: String?,
) =
    AndroidDataStore(this@logVerifySuccess)
        .store(
            SafeToRunEvents.SuccessVerify(
                appMetadata = metadata(),
                checkName = checkName,
                deviceInformation = DeviceInformation.empty(),
                extra = extraInfo
            )
        )

internal suspend fun Context.logCheckSuccess(checkName: String) =
    AndroidDataStore(this@logCheckSuccess)
        .store(
            SafeToRunEvents.SucceedCheck(
                appMetadata = metadata(),
                checkName = checkName,
                deviceInformation = DeviceInformation.empty()
            )
        )

/**
 * Retrieve all logs previously stored
 */
suspend fun Context.logs() = AndroidDataStore(this).retrieve()

/**
 * Remove all logs previously stored
 */
suspend fun Context.clearLogs() = AndroidDataStore(this).clear()

/**
 * Remove a specific log
 */
suspend fun Context.deleteLog(uuid: String) = AndroidDataStore(this).delete(uuid)

