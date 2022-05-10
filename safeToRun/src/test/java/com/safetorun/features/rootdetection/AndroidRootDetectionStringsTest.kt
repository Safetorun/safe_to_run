package com.safetorun.features.rootdetection

import android.content.Context
import android.content.res.Resources
import com.safetorun.R
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class AndroidRootDetectionStringsTest : TestCase() {
    private val resources = mockk<Resources>()
    private val context = mockk<Context>()

    override fun setUp() {
        every { context.resources } returns resources
        every { resources.getString(R.string.root_detection_passed) } returns OK_MESSAGE
        every { resources.getString(R.string.root_detection_did_not_run) } returns NOT_RUN
        every { resources.getString(R.string.root_detection_failed) } returns FAILED
    }

    fun `test that strings are correct as they are called`() {

        with(AndroidRootDetectionStrings(context)) {
            assertThat(rootDetectionDidNotRun()).isEqualTo(NOT_RUN)
            assertThat(rootDetectionFailedMessage()).isEqualTo(FAILED)
            assertThat(rootDetectionPassedMessage()).isEqualTo(OK_MESSAGE)
        }
    }

    companion object {
        const val OK_MESSAGE = "Ok"
        const val NOT_RUN = "NOT RUN"
        const val FAILED = "FAILED"
    }
}
