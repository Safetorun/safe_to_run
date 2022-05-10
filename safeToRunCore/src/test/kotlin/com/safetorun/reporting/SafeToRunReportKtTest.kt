package com.safetorun.reporting

import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase

internal class SafeToRunReportKtTest : TestCase() {

    fun `test that safe to run reports failures if there are any failures`() {
        val safeToRunResponse = SafeToRunReport.MultipleReports(
            listOf(
                SafeToRunReport.MultipleReports(
                    listOf(
                        SafeToRunReport.SafeToRunReportFailure("Abc", "Abc"),
                        SafeToRunReport.SafeToRunReportSuccess("Def")
                    )
                ),
                SafeToRunReport.SafeToRunReportSuccess("Def"),
                SafeToRunReport.SafeToRunReportSuccess("Def"),
                SafeToRunReport.SafeToRunWarning("Def", ""),
            )
        ).anyFailures()

        assertThat(safeToRunResponse).isTrue()
    }

    fun `test that safe to run reports no failures if there are no failures`() {
        val safeToRunResponse = SafeToRunReport.MultipleReports(
            listOf(
                SafeToRunReport.MultipleReports(
                    listOf(
                        SafeToRunReport.SafeToRunReportSuccess("Def")
                    )
                ),
                SafeToRunReport.SafeToRunReportSuccess("Def"),
                SafeToRunReport.SafeToRunReportSuccess("Def"),
                SafeToRunReport.SafeToRunWarning("Def", ""),
            )
        ).anyFailures()

        assertThat(safeToRunResponse).isFalse()
    }
}
