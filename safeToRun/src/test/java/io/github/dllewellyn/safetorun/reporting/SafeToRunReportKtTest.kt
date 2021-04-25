package io.github.dllewellyn.safetorun.reporting

import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase

internal class SafeToRunReportKtTest : TestCase() {

    private val failures = listOf(
        SafeToRunReport.SafeToRunReportFailure("A", "B"),
        SafeToRunReport.SafeToRunReportFailure("D", "E"),
        SafeToRunReport.SafeToRunReportFailure("F", "G")
    )

    private val warnings = listOf(
        SafeToRunReport.SafeToRunWarning("H", "I"),
        SafeToRunReport.SafeToRunWarning("J", "K"),
        SafeToRunReport.SafeToRunWarning("L", "M")
    )

    private val multiSuccess = listOf(
        SafeToRunReport.SafeToRunReportSuccess("O"),
        SafeToRunReport.SafeToRunReportSuccess("P")
    )

    private val success = listOf(
        SafeToRunReport.SafeToRunReportSuccess("N"),
        SafeToRunReport.MultipleReports(multiSuccess)
    )


    fun `test that a grouped report returns correct result`() {
        val reports = SafeToRunReport.MultipleReports(listOf(failures, warnings, success).flatten())
            .toGrouped()

        with(reports) {
            assertThat(failedReports).isEqualTo(failures)
            assertThat(warningReports).isEqualTo(warnings)
            assertThat(successReports).isEqualTo(
                listOf(
                    listOf(success.first() as SafeToRunReport.SafeToRunReportSuccess),
                    multiSuccess
                ).flatten()
            )
        }
    }
}