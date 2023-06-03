package com.safetorun.reporting

import android.content.Context
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.google.common.truth.Truth.assertThat
import com.safetorun.logger.deleteLog
import com.safetorun.logger.logs
import com.safetorun.logger.models.SafeToRunEvents
import com.safetorun.offdevice.safeToRunLogger
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.test.runTest

@ExperimentalCoroutinesApi
internal class LoggerBackendSynchroniserTest : TestCase() {

    private val context by lazy {
        mockk<Context>()
    }

    private val listAtEnd = mutableListOf<SafeToRunEvents>()

    private val returnList = listOf(
        SafeToRunEvents.FailedCheck.empty("default"),
        SafeToRunEvents.SucceedCheck.empty("default")
    )

    override fun setUp() {
        mockkStatic("com.safetorun.offdevice.AndroidSafeToRunOffDeviceKt")
        mockkStatic("com.safetorun.logger.BackendSynchKt")

        every { context.safeToRunLogger(any()) } returns {
            listAtEnd.add(it)
        }

        coEvery { context.logs() } coAnswers { returnList.asFlow() }
        coEvery { context.deleteLog(any()) } coAnswers { /** Noop **/ }
    }

    fun `test that all items returned from logs are synchronised with the backend`() = runTest {
        val inputData = workDataOf(LoggerBackendSynchroniser.KEY_API_KEY to "Abc")
        listAtEnd.clear()

        LoggerBackendSynchroniser(context, mockk<WorkerParameters>(relaxed = true).also {
            every { it.inputData } returns inputData
        }).doWork()

        this.testScheduler.runCurrent()

        assertThat(listAtEnd).containsExactlyElementsIn(returnList)
    }
}
