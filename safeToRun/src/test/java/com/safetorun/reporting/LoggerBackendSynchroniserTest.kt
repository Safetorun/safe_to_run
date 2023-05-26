package com.safetorun.reporting

import android.content.Context
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.google.common.truth.Truth.assertThat
import com.safetorun.logger.logs
import com.safetorun.logger.models.AppMetadata
import com.safetorun.logger.models.DeviceInformation
import com.safetorun.logger.models.SafeToRunEvents
import com.safetorun.offdevice.safeToRunLogger
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class LoggerBackendSynchroniserTest {

    private val context = mockk<Context>()
        .apply {
            coEvery { logs() } coAnswers { returnList.asFlow() }
        }

    private val returnList = listOf(
        failedCheck(),
        successCheck()
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test that all items returned from logs are synchronised with the backend`() = runTest {

        val listAtEnd = mutableListOf<SafeToRunEvents>()
        val inputData = workDataOf(LoggerBackendSynchroniser.KEY_API_KEY to "Abc")

        every { context.safeToRunLogger(any()) } returns {
            listAtEnd.add(it)
        }

        LoggerBackendSynchroniser(context, mockk<WorkerParameters>().also {
            every { it.inputData } returns inputData
        }).doWork()

        assertThat(listAtEnd).containsExactlyElementsIn(returnList)
    }

    private fun failedCheck() = SafeToRunEvents.FailedCheck(
        DeviceInformation.empty(),
        AppMetadata.empty(),
        "default"
    )

    private fun successCheck() = SafeToRunEvents.SucceedCheck(
        DeviceInformation.empty(),
        AppMetadata.empty(),
        "default"
    )
}
