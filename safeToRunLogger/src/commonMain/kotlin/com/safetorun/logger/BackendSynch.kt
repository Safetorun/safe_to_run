package com.safetorun.logger

import android.content.Context
import com.safetorun.logger.metadata.metadata
import com.safetorun.logger.models.DeviceInformation
import com.safetorun.logger.models.SafeToRunEvents
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

fun Context.loggerForCheck(apiKey : String, checkName: String): (Boolean) -> Unit = {
    if (it) {
        logCheckSuccess(checkName)
    } else {
        logCheckFailure(checkName)
    }
}

fun Context.logCheckFailure(checkName: String) = GlobalScope.launch {
    AndroidDataStore(this@logCheckFailure)
        .store(
            SafeToRunEvents.FailedCheck(
                appMetadata = metadata(),
                checkName = checkName,
                deviceInformation = DeviceInformation.empty()
            )
        )
}


fun Context.logCheckSuccess(checkName: String) = GlobalScope.launch {
    AndroidDataStore(this@logCheckSuccess)
        .store(
            SafeToRunEvents.SucceedCheck(
                appMetadata = metadata(),
                checkName = checkName,
                deviceInformation = DeviceInformation.empty()
            )
        )
}

fun Context.getLogs(onEach: (SafeToRunEvents) -> Unit) = GlobalScope.launch {
    AndroidDataStore(this@getLogs)
        .retrieve()
        .onEach { onEach(it) }
        .collect()
}
