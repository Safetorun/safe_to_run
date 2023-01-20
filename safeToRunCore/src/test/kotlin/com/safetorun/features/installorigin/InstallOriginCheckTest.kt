package com.safetorun.features.installorigin

import com.google.common.truth.Truth.assertThat
import com.safetorun.reporting.SafeToRunReport
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class InstallOriginCheckTest : TestCase() {

    private val strings = mockk<InstallOriginStrings>()
    private val installOriginQuery = mockk<InstallOriginQuery>()

    override fun setUp() {

        every { strings.couldNotFindPackage() } returns NOT_FOUND
        every { strings.packageWasNotInAllowedList(any()) } answers {
            NOT_MATCHING.format(args[0].toStr().removeSuffix("]").removePrefix("["))
        }
        every { strings.packageWasInAllowedList() } returns MATCHED

        every { installOriginQuery.getInstallPackageName() } returns PACKAGE_NAME_RETURNS
    }

    fun `test that if the package returns a different package to allowed it will return an error`() {
        // Given
        val installOriginCheck = InstallOriginCheck(strings, listOf(InstallOrigin("")), installOriginQuery)

        // When
        val result = installOriginCheck.canRun() as SafeToRunReport.SafeToRunReportFailure

        // Then
        assertThat(result.failureMessage).isEqualTo(NOT_MATCHING.format(PACKAGE_NAME_RETURNS))
    }

    fun `test that if the package returns an allowed package we return success`() {
        // Given
        val installOriginCheck =
            InstallOriginCheck(strings, listOf(InstallOrigin(PACKAGE_NAME_RETURNS)), installOriginQuery)

        // When
        val result = installOriginCheck.canRun() as SafeToRunReport.SafeToRunReportSuccess

        // Then
        assertThat(result.successMessage).isEqualTo(MATCHED)
    }

    fun `test assert to string of origin install`() {
        assertThat(InstallOrigin(PACKAGE_NAME_RETURNS).originPackage).isEqualTo(PACKAGE_NAME_RETURNS)
    }

    fun `test with some integration logic`() {
        // Given
        every { installOriginQuery.getInstallPackageName() } returns GooglePlayStore().originPackage

        val installOriginCheck = installOrigin(installOriginQuery, strings) {
            +GooglePlayStore()
            +AmazonStore()
        }

        // When
        val result = installOriginCheck.canRun() as SafeToRunReport.SafeToRunReportSuccess

        // Then
        assertThat(result.successMessage).isEqualTo(MATCHED)
    }

    companion object {
        const val NOT_FOUND = "NOT FOUND"
        const val NOT_MATCHING = "Not matching %s"
        const val MATCHED = "Matched"
        const val PACKAGE_NAME_RETURNS = "com.package.name"
    }
}
