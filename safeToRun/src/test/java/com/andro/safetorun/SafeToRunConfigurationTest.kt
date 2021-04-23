package com.andro.safetorun

import com.andro.safetorun.checks.SafeToRunCheck
import com.andro.safetorun.reporting.SafeToRunReport
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import org.junit.jupiter.api.Test

internal class SafeToRunConfigurationTest : TestCase() {

    @MockK
    lateinit var check1: SafeToRunCheck

    @MockK
    lateinit var check2: SafeToRunCheck

    @MockK
    lateinit var check3: SafeToRunCheck


    private val result1 = SafeToRunReport.SafeToRunReportSuccess("")
    private val result2 = SafeToRunReport.SafeToRunReportSuccess("")

    override fun setUp() {
        MockKAnnotations.init(this)
        every { check1.canRun() } returns result1
        every { check2.canRun() } returns result2
    }

    @Test
    fun `test that errors passed into warn are converted to warn check`() {
        // Given

        val failureReason = "fr"
        val failureMessage = "fm"

        val result3 = SafeToRunReport.SafeToRunReportFailure(failureReason, failureMessage)

        SafeToRun.init(
            configure {
                this errorIf check1
                this errorIf check2
                this warnIf check3
            }
        )

        every { check3.canRun() } returns result3

        // When
        val result = SafeToRun.isSafeToRun() as SafeToRunReport.MultipleReports

        // Then
        Truth.assertThat(result.reports).containsExactly(
            result1,
            result2,
            SafeToRunReport.SafeToRunWarning(failureReason, failureMessage)
        )
    }

    @Test
    fun `test that errors passed into warn are converted to warn check when given as a list`() {
        // Given

        val failureReason = "fr"
        val failureMessage = "fm"

        val failureReason2 = "fr2"
        val failureMessage2 = "fm2"

        val result3 = SafeToRunReport.SafeToRunReportFailure(failureReason, failureMessage)
        val result4 = SafeToRunReport.SafeToRunReportFailure(failureReason2, failureMessage2)

        SafeToRun.init(
            configure {
                this errorIf check1
                this errorIf check2
                this warnIf check3
            }
        )

        every { check3.canRun() } returns SafeToRunReport.MultipleReports(listOf(result3, result4))

        // When
        val result = SafeToRun.isSafeToRun() as SafeToRunReport.MultipleReports

        // Then
        Truth.assertThat(result.reports).containsExactly(
            result1,
            result2,
            SafeToRunReport.MultipleReports(
                listOf(
                    SafeToRunReport.SafeToRunWarning(failureReason, failureMessage),
                    SafeToRunReport.SafeToRunWarning(failureReason2, failureMessage2)
                )
            )
        )
    }
}