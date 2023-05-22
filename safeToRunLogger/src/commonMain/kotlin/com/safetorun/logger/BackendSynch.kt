package com.safetorun.logger

import android.content.Context
import com.safetorun.logger.metadata.metadata
import com.safetorun.logger.models.DeviceInformation
import com.safetorun.logger.models.SafeToRunEvents
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Retrieve a logger for a given check
 *
 * @param checkName a unique name to describe your check
 */
fun Context.loggerForCheck(checkName: String): (Boolean) -> Unit = {
    if (it) {
        logCheckSuccess(checkName)
    } else {
        logCheckFailure(checkName)
    }
}

internal fun Context.logCheckFailure(checkName: String) = GlobalScope.launch {
    AndroidDataStore(this@logCheckFailure)
        .store(
            SafeToRunEvents.FailedCheck(
                appMetadata = metadata(),
                checkName = checkName,
                deviceInformation = DeviceInformation.empty()
            )
        )
}


internal fun Context.logCheckSuccess(checkName: String) = GlobalScope.launch {
    AndroidDataStore(this@logCheckSuccess)
        .store(
            SafeToRunEvents.SucceedCheck(
                appMetadata = metadata(),
                checkName = checkName,
                deviceInformation = DeviceInformation.empty()
            )
        )
}

/**
 * Retrieve all logs previously stored
 *
 * @param onEach a callback to be called for each log
 *
 * @return a job that can be cancelled
 */
fun Context.getLogs(onEach: (SafeToRunEvents) -> Unit) = GlobalScope.launch {
    logs()
        .onEach { onEach(it) }
        .collect()
}

suspend fun Context.logs() = AndroidDataStore(this).retrieve()

