package com.andro.safetorun.features.rootdetection

import com.andro.safetorun.reporting.SafeToRunReport
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase

internal class RootBeerRootDetectionTest : TestCase() {

    private val mockStrings = mockk<RootDetectionStrings>()
    private val rootDetectionChecker = mockk<RootDetectionChecker>()


    override fun setUp() {
        every { mockStrings.rootDetectionPassedMessage() } returns OK_MESSAGE
        every { mockStrings.rootDetectionDidNotRun() } returns NOT_RUN
        every { mockStrings.rootDetectionFailedMessage() } returns FAILED
    }

    fun `test that if we tolerate root we will pass`() {
        // Given
        val config = RootDetectionConfig().apply {
            tolerateRoot = true
        }

        // When
        val checker = createRootBeerRootDetection(config).canRun() as SafeToRunReport.SafeToRunReportSuccess

        // Then
        assertThat(checker.successMessage).isEqualTo(NOT_RUN)
    }

    fun `test that if we do not root or busybox we will pass if root checker is run`() {
        // Given
        val config = RootDetectionConfig().apply {
            tolerateRoot = false
            tolerateBusyBox = false
        }

        every { rootDetectionChecker.isRooted() } returns false

        // When
        val checker = createRootBeerRootDetection(config).canRun() as SafeToRunReport.SafeToRunReportSuccess

        // Then
        assertThat(checker.successMessage).isEqualTo(OK_MESSAGE)
        verify { rootDetectionChecker.isRooted() }
    }

    fun `test that if we do not root or busybox we will fail if root checker is run`() {
        // Given
        val config = RootDetectionConfig().apply {
            tolerateRoot = false
            tolerateBusyBox = false
        }

        every { rootDetectionChecker.isRooted() } returns true

        // When
        val checker = createRootBeerRootDetection(config).canRun() as SafeToRunReport.SafeToRunReportFailure

        // Then
        assertThat(checker.failureMessage).isEqualTo(FAILED)
        verify { rootDetectionChecker.isRooted() }
    }

    fun `test that if we do tolerate busybox we will pass if root checker is run`() {
        // Given
        val config = RootDetectionConfig().apply {
            tolerateRoot = false
            tolerateBusyBox = true
        }

        every { rootDetectionChecker.isRootedWithBusyBoxCheck() } returns false

        // When
        val checker = createRootBeerRootDetection(config).canRun() as SafeToRunReport.SafeToRunReportSuccess

        // Then
        assertThat(checker.successMessage).isEqualTo(OK_MESSAGE)
        verify { rootDetectionChecker.isRootedWithBusyBoxCheck() }
    }

    fun `test that if we do not tolerate busybox we will fail if root checker is run`() {
        // Given
        val config = RootDetectionConfig().apply {
            tolerateRoot = false
            tolerateBusyBox = true
        }

        every { rootDetectionChecker.isRootedWithBusyBoxCheck() } returns true

        // When
        val checker = createRootBeerRootDetection(config).canRun() as SafeToRunReport.SafeToRunReportFailure

        // Then
        assertThat(checker.failureMessage).isEqualTo(FAILED)
        verify { rootDetectionChecker.isRootedWithBusyBoxCheck() }
    }

    private fun createRootBeerRootDetection(config: RootDetectionConfig) =
        RootBeerRootDetection(config, rootDetectionChecker, mockStrings)

    companion object {
        const val OK_MESSAGE = "Ok"
        const val NOT_RUN = "NOT RUN"
        const val FAILED = "FAILED"
    }
}