package io.github.dllewellyn.safetorun.features.blacklistedapps

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.checks.SafeToRunCheck
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport
import io.github.dllewellyn.safetorun.stringWithoutSuffixForArg
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class DefaultBlacklistedAppCheckTest : TestCase() {

    private val blacklistedCheck = mockk<BlacklistedAppCheck>()
    private val mockStrings = mockk<BlacklistedAppStrings>()

    override fun setUp() {
        every { blacklistedCheck.isAppPresent(NOT_PRESENT_PACKAGE) } returns false
        every { blacklistedCheck.isAppPresent(IS_PRESENT_PACKAGE) } returns true
        every { mockStrings.didNotFindBlacklistedAppMessage() } returns NOT_FOUND_BLACKLISTED
        every { mockStrings.foundBlacklistedAppMessage(any()) } answers {
            stringWithoutSuffixForArg(0)
        }
    }

    fun `test that calling blacklisted apps with a package existing`() {
        // Given

        // When
        val result = blacklistedConf {
            +NOT_PRESENT_PACKAGE
        }.canRun() as SafeToRunReport.SafeToRunReportSuccess

        // Then
        assertThat(result.successMessage).isEqualTo(NOT_FOUND_BLACKLISTED)
    }

    fun `test that calling blacklisted apps with a package not existing`() {
        // Given

        // When
        val result = blacklistedConf {
            +IS_PRESENT_PACKAGE
        }.canRun() as SafeToRunReport.MultipleReports

        // Then
        with(result.reports.first() as SafeToRunReport.SafeToRunReportFailure) {
            assertThat(failureMessage).isNotNull()
        }
    }

    private fun blacklistedConf(block: BlacklistedAppConfiguration.() -> Unit): SafeToRunCheck =
        blacklistedAppConfiguration(blacklistedCheck, mockStrings, block)

    companion object {
        private const val IS_PRESENT_PACKAGE = "com.abc.com"
        private const val NOT_PRESENT_PACKAGE = "com.abc.def"
        private const val NOT_FOUND_BLACKLISTED = "Not found strings"
    }
}
