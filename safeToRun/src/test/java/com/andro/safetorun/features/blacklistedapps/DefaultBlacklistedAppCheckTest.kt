package com.andro.safetorun.features.blacklistedapps

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.andro.safetorun.reporting.SafeToRunReport
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class DefaultBlacklistedAppCheckTest : TestCase() {

    private val mockContext = mockk<Context>(relaxed = true)
    private val mockPackageManager = mockk<PackageManager>()
    private val mockPackage1 = mockk<PackageInfo>()
    private val packageInfos = listOf(mockPackage1)


    override fun setUp() {
        every { mockContext.packageManager } returns mockPackageManager
        every { mockPackageManager.getInstalledPackages(any()) } returns packageInfos

        mockPackage1.packageName = IS_PRESENT_PACKAGE
    }

    fun `test that calling blacklisted apps with a package existing`() {
        // Given

        // When
        val result = mockContext.blacklistConfiguration {
            +NOT_PRESENT_PACKAGE
        }.canRun(mockContext) as SafeToRunReport.SafeToRunReportSuccess

        // Then
        assertThat(result.successMessage).isNotNull()

    }

    fun `test that calling blacklisted apps with a package not existing`() {
        // Given

        // When
        val result = mockContext.blacklistConfiguration {
            +IS_PRESENT_PACKAGE
        }.canRun(mockContext) as SafeToRunReport.MultipleReports

        // Then
        with(result.reports.first() as SafeToRunReport.SafeToRunReportFailure) {
            assertThat(failureMessage).isNotNull()
        }
    }

    fun `test that the builder function with context returns an object that works`() {
        assertThat(mockContext.blacklistConfiguration {}).isNotNull()
    }

    companion object {
        private const val IS_PRESENT_PACKAGE = "com.abc.com"
        private const val NOT_PRESENT_PACKAGE = "com.abc.def"
    }
}