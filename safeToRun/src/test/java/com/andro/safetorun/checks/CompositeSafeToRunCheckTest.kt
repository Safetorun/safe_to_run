package com.andro.safetorun.checks

import android.content.Context
import com.andro.safetorun.reporting.SafeToRunReport
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import org.junit.jupiter.api.Test

class CompositeSafeToRunCheckTest : TestCase() {

    @MockK
    lateinit var check1: SafeToRunCheck

    @MockK
    lateinit var check2: SafeToRunCheck

    private val compositeSafeToRunCheck by lazy {
        CompositeSafeToRunCheck(listOf(check1, check2))
    }

    override fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test that composite class runs other safe to run check`() {

        // Given
        val result1 = SafeToRunReport.SafeToRunReportSuccess("")
        val result2 = SafeToRunReport.SafeToRunReportSuccess("")

        every { check1.canRun() } returns result1
        every { check2.canRun() } returns result2

        // When
        val result = compositeSafeToRunCheck.canRun() as SafeToRunReport.MultipleReports

        // Then
        assertThat(result.reports).containsExactly(result1, result2)
    }
}