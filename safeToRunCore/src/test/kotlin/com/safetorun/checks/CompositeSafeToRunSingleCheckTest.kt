package com.safetorun.checks

import com.google.common.truth.Truth.assertThat
import com.safetorun.reporting.SafeToRunReport
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import org.junit.jupiter.api.Test

class CompositeSafeToRunSingleCheckTest : TestCase() {

    @MockK
    lateinit var check1: SafeToRunCheck

    @MockK
    lateinit var check2: SafeToRunCheck

    @MockK
    lateinit var check3: SafeToRunCheck

    private val result1 = SafeToRunReport.SafeToRunReportSuccess("")
    private val result2 = SafeToRunReport.SafeToRunReportSuccess("")

    private val compositeSafeToRunCheck by lazy {
        CompositeSafeToRunCheck(listOf(check1, check2), emptyList())
    }

    override fun setUp() {
        MockKAnnotations.init(this)
        every { check1.canRun() } returns result1
        every { check2.canRun() } returns result2
    }

    @Test
    fun `test that composite class runs other safe to run check`() {

        // Given
        // When
        val result = compositeSafeToRunCheck.canRun() as SafeToRunReport.MultipleReports

        // Then
        assertThat(result.reports).containsExactly(result1, result2)
    }

    @Test
    fun `test that errors passed into warn are converted to warn check`() {
        // Given

        val failureReason = "fr"
        val failureMessage = "fm"

        val result3 = SafeToRunReport.SafeToRunReportFailure(failureReason, failureMessage)

        val composeCheck = CompositeSafeToRunCheck(listOf(check1, check2), listOf(check3))

        every { check3.canRun() } returns result3

        // When
        val result = composeCheck.canRun() as SafeToRunReport.MultipleReports

        // Then
        assertThat(result.reports).containsExactly(
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

        val composeCheck = CompositeSafeToRunCheck(listOf(check1, check2), listOf(check3))

        every { check3.canRun() } returns SafeToRunReport.MultipleReports(listOf(result3, result4))

        // When
        val result = composeCheck.canRun() as SafeToRunReport.MultipleReports

        // Then
        assertThat(result.reports).containsExactly(
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

    @Test
    fun `test that errors passed into warn are converted to warn check when given as a list diff order`() {
        // Given

        val failureReason = "fr"
        val failureMessage = "fm"

        val failureReason2 = "fr2"
        val failureMessage2 = "fm2"

        val result3 = SafeToRunReport.SafeToRunReportFailure(failureReason, failureMessage)
        val result4 = SafeToRunReport.SafeToRunReportFailure(failureReason2, failureMessage2)

        val composeCheck = CompositeSafeToRunCheck(listOf(check1, check2), listOf(check3))

        every { check1.canRun() } returns result3
        every { check3.canRun() } returns SafeToRunReport.MultipleReports(listOf(result1, result4))

        // When
        val result = composeCheck.canRun() as SafeToRunReport.MultipleReports

        // Then
        assertThat(result.reports).containsExactly(
            result3,
            result2,
            SafeToRunReport.MultipleReports(
                listOf(
                    result1,
                    SafeToRunReport.SafeToRunWarning(failureReason2, failureMessage2)
                )
            )
        )
    }
}
