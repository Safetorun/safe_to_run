package com.andro.safetorun.features.blacklistedapps

import android.content.Context
import android.content.res.Resources
import com.andro.safetorun.reporting.SafeToRunReport
import com.google.common.truth.Truth.assertThat
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase


class BlacklistedAppConfigurationTest : TestCase() {

    private val blacklistedAppCheck = mockk<BlacklistedAppCheck>(relaxed = true)
    private val mockContext = mockk<Context>()
    private val mockkResources = mockk<Resources>()

    override fun setUp() {
        every { blacklistedAppCheck.isAppPresent(IS_PRESENT_PACKAGE) } returns true
        every { blacklistedAppCheck.isAppPresent(NOT_PRESENT_PACKAGE) } returns false
        every { mockContext.resources } returns mockkResources
    }

    fun `test we configure our blacklisted app to fail if com abc com exists can run should fail`() {
        every { mockkResources.getString(any(), any()) } answers {
            args[1].toStr()
        }

        val conf = blacklistConfiguration(blacklistedAppCheck) {
            +IS_PRESENT_PACKAGE
        }

        val result = conf.canRun(mockContext) as SafeToRunReport.MultipleReports
        with(result.reports.first() as SafeToRunReport.SafeToRunReportFailure) {
            assertThat(failureMessage).contains(IS_PRESENT_PACKAGE)
        }
    }

    fun `test we configure our blacklisted app to fail with def can run should succeed`() {
        every { mockkResources.getString(any()) } returns "Message"
        val conf = blacklistConfiguration(blacklistedAppCheck) {
            +NOT_PRESENT_PACKAGE
        }

        val result = conf.canRun(mockContext) as SafeToRunReport.SafeToRunReportSuccess
        assertThat(result.successMessage).isNotEmpty()
    }

    fun `test we configure our blacklisted app to fail with both can run should fail`() {
        every { mockkResources.getString(any(), any()) } answers {
            args[1].toStr()
        }

        val conf = blacklistConfiguration(blacklistedAppCheck) {
            +NOT_PRESENT_PACKAGE
            +IS_PRESENT_PACKAGE
        }

        val result = conf.canRun(mockContext) as SafeToRunReport.MultipleReports

        with(result.reports.first() as SafeToRunReport.SafeToRunReportFailure) {
            assertThat(failureMessage).contains(IS_PRESENT_PACKAGE)
        }
    }

    companion object {
        private const val IS_PRESENT_PACKAGE = "com.abc.com"
        private const val NOT_PRESENT_PACKAGE = "com.abc.def"
    }
}