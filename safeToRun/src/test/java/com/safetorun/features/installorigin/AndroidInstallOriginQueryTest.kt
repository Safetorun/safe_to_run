package com.safetorun.features.installorigin

import android.content.Context
import android.content.pm.InstallSourceInfo
import android.content.pm.PackageManager
import com.google.common.truth.Truth.assertThat
import com.safetorun.features.installorigin.SharedInstallOrigin.MATCHED
import com.safetorun.features.installorigin.SharedInstallOrigin.NOT_FOUND
import com.safetorun.features.installorigin.SharedInstallOrigin.NOT_MATCHING
import com.safetorun.features.installorigin.SharedInstallOrigin.PACKAGE_NAME_RETURNS
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class AndroidInstallOriginQueryTest : TestCase() {

    private val strings = mockk<InstallOriginStrings>()
    private val context = mockk<Context>()

    private val packageManager = mockk<PackageManager>()
    private val versions = listOf(0, 31)
    override fun setUp() {

        every { strings.couldNotFindPackage() } returns NOT_FOUND
        every { strings.packageWasNotInAllowedList(any()) } answers {
            NOT_MATCHING.format(args[0].toStr().removeSuffix("]").removePrefix("["))
        }
        every { strings.packageWasInAllowedList() } returns MATCHED
        every { context.packageManager } returns packageManager
        every { packageManager.getInstallerPackageName(any()) } returns PACKAGE_NAME_RETURNS
        every { context.packageName } returns "com.anything"
        every { packageManager.getInstallSourceInfo(any()) } returns mockk<InstallSourceInfo>().apply {
            every { installingPackageName } returns PACKAGE_NAME_RETURNS
        }

        every { context.resources } returns SharedInstallOrigin.setupAMockResources()
    }

    fun `test that if the package returns null we return the null message`() {
        // Given
        versions.forEach { version ->
            every { packageManager.getInstallerPackageName(any()) } returns null
            every { packageManager.getInstallSourceInfo(any()) } returns mockk<InstallSourceInfo>().apply {
                every { installingPackageName } returns null
            }
            val installOriginQuery = InstallOriginQuery { context.getInstaller(version) }

            // When
            val result = installOriginQuery.getInstallPackageName()

            // Then
            assertThat(result).isNull()
        }
    }

    fun `test that if the package returns a different package to allowed it will return an error`() {
        // Given
        versions.forEach { version ->
            val installOriginQuery = InstallOriginQuery { context.getInstaller(version) }

            // When
            val result = installOriginQuery.getInstallPackageName()

            // Then
            assertThat(result).isEqualTo(PACKAGE_NAME_RETURNS)
        }
    }

    fun `test using builder returns correct`() {
        // Given // When
        val result =
            context.installOriginCheckWithDefaults(PACKAGE_NAME_RETURNS)
                .canRun() as SafeToRunReport.SafeToRunReportSuccess

        // Then
        assertThat(result.successMessage).isEqualTo(MATCHED)
    }
}
