package io.github.dllewellyn.safetorun.features.blacklistedapps

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

class BlacklistedAppConfigurationTest : TestCase() {
    private val blacklistedAppCheck = mockk<BlacklistedAppCheck>(relaxed = true)
    private val mockStringsConfiguration = mockk<BlacklistedAppStrings>()

    override fun setUp() {
        every { blacklistedAppCheck.isAppPresent(IS_PRESENT_PACKAGE) } returns true
        every { blacklistedAppCheck.isAppPresent(NOT_PRESENT_PACKAGE) } returns false
    }

    fun `test we configure our blacklisted app to fail if com abc com exists can run should fail`() {
        every { mockStringsConfiguration.foundBlacklistedAppMessage(any()) } answers {
            args[0].toStr()
        }

        val conf = blacklistConfig(blacklistedAppCheck, mockStringsConfiguration) {
            +IS_PRESENT_PACKAGE
        }

        val result = conf.canRun() as SafeToRunReport.MultipleReports
        with(result.reports.first() as SafeToRunReport.SafeToRunReportFailure) {
            assertThat(failureMessage).contains(IS_PRESENT_PACKAGE)
        }
    }

    fun `test we configure our blacklisted app to fail with def can run should succeed`() {
        every { mockStringsConfiguration.didNotFindBlacklistedAppMessage() } returns "Message"
        val conf = blacklistConfig(blacklistedAppCheck, mockStringsConfiguration) {
            +NOT_PRESENT_PACKAGE
        }

        val result = conf.canRun() as SafeToRunReport.SafeToRunReportSuccess
        assertThat(result.successMessage).isNotEmpty()
    }

    fun `test we configure our blacklisted app to fail with both can run should fail`() {
        every { mockStringsConfiguration.foundBlacklistedAppMessage(any()) } answers {
            args[0].toStr()
        }

        val conf = blacklistConfig(blacklistedAppCheck, mockStringsConfiguration) {
            +NOT_PRESENT_PACKAGE
            +IS_PRESENT_PACKAGE
        }

        val result = conf.canRun() as SafeToRunReport.MultipleReports

        with(result.reports.first() as SafeToRunReport.SafeToRunReportFailure) {
            assertThat(failureMessage).contains(IS_PRESENT_PACKAGE)
        }
    }

    companion object {
        private const val IS_PRESENT_PACKAGE = "com.abc.com"
        private const val NOT_PRESENT_PACKAGE = "com.abc.def"
    }
}
