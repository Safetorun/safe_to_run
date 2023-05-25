package com.safetorun.reporting

import android.content.Context
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
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LoggerBackendSynchroniserTest {

    private val context = mockk<Context>()
    private val returnList = listOf(
        failedCheck(),
        successCheck()
    )

    @Before
    fun before() {
        coEvery { context.logs() } coAnswers { returnList.asFlow() }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test that all items returned from logs are synchronised with the backend`() = runTest {
        val listAtEnd = mutableListOf<SafeToRunEvents>()

        every { context.safeToRunLogger(any()) } returns {
            listAtEnd.add(it)
        }

        LoggerBackendSynchroniser(context, mockk()).doWork()
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
