package com.safetorun.reporting

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.safetorun.logger.getLogs
import com.safetorun.offdevice.safeToRunLogger

internal class LoggerBackendSynchroniser(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val apiKey = inputData.getString(KEY_API_KEY)

        return try {
            if (apiKey != null) {

                val logger = this.applicationContext.safeToRunLogger(apiKey)

                this.applicationContext.getLogs {
                    logger.invoke(it)
                }

                Result.success()
            } else {
                Result.failure()
            }

        } catch (e: Exception) {
            @Suppress("Detekt:TooGenericException", "Detekt:SwallowedException")
            Result.failure()
        }
    }

    companion object {
        const val KEY_API_KEY = "apiKey"
    }

}
