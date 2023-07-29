package com.safetorun.plus

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.safetorun.logger.deleteLog
import com.safetorun.logger.logs
import com.safetorun.plus.queries.getInstaller
import com.safetorun.plus.queries.rootDetectionCheck
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

internal class LoggerBackendSynchroniser(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    @Suppress("TooGenericExceptionCaught", "SwallowedException")
    override suspend fun doWork(): Result {
        val apiKey = inputData.getString(KEY_API_KEY)

        return try {
            if (apiKey != null) {

                val logger = this.applicationContext.safeToRunLogger(
                    apiKey,
                    { this.applicationContext.getInstaller() },
                    this.applicationContext::rootDetectionCheck,
                )

                this.applicationContext.logs()
                    .onEach {
                        logger.invoke(it)
                        this.applicationContext.deleteLog(it.uuid)
                    }
                    .collect()


                Result.success()
            } else {
                Result.failure()
            }


        } catch (e: Exception) {
            Result.failure()
        }
    }

    companion object {
        const val KEY_API_KEY = "apiKey"
    }

}
