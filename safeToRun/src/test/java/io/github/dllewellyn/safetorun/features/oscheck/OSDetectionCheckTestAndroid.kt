package io.github.dllewellyn.safetorun.features.oscheck

import android.content.Context
import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.conditional.conditionalBuilder
import io.github.dllewellyn.safetorun.features.oscheck.OSConfiguration.minOsVersion
import io.github.dllewellyn.safetorun.features.oscheck.OSConfiguration.notManufacturer
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class OSDetectionCheckTestAndroid : TestCase() {

    private val osInformationQuery = mockk<OSInformationQuery>()

    override fun setUp() {
        OSConfiguration.osInformationQuery = osInformationQuery
    }

    fun `test that we can create a os version rule that fails if os is too low`() {
        // Given
        every { osInformationQuery.osVersion() } returns 29

        // When
        val result = mockk<Context>().osDetectionCheck(
            minOsVersion(
                30
            )
        ).canRun() as SafeToRunReport.MultipleReports

        // Then
        with(result.reports.first() as SafeToRunReport.SafeToRunReportFailure) {
            assertThat(failureMessage).isNotNull()
            assertThat(failureMessage).contains("30")
            assertThat(failureMessage).contains("29")
        }
    }

    fun `test that we can create a os version rule that passes if os is high enough`() {
        // Given
        every { osInformationQuery.osVersion() } returns 30

        // When
        val result = mockk<Context>(relaxed = true).osDetectionCheck(
            minOsVersion(
                30
            )
        ).canRun() as SafeToRunReport.SafeToRunReportSuccess

        // Then
        assertThat(result.successMessage).isNotNull()
    }

    fun `test that we can create a rule that will fail if manufacturer is dodgy`() {
        // Given
        every { osInformationQuery.manufacturer() } returns DODGY_MANUFACTURER
        // When
        val result = mockk<Context>().osDetectionCheck(
            notManufacturer(
                DODGY_MANUFACTURER
            )
        ).canRun() as SafeToRunReport.MultipleReports

        // Then
        with(result.reports.first() as SafeToRunReport.SafeToRunReportFailure) {
            assertThat(failureMessage).isNotNull()
            assertThat(failureMessage).contains(DODGY_MANUFACTURER)
        }
    }

    fun `test that we can create a rule that will fail if manufacturer and os version`() {
        // Given
        every { osInformationQuery.osVersion() } returns 30
        every { osInformationQuery.manufacturer() } returns DODGY_MANUFACTURER

        val conditional = conditionalBuilder {
            with(osInformationQuery) {
                with(minOsVersion(30))
                and(notManufacturer(DODGY_MANUFACTURER))
            }
        }

        val osDetectionRule = mockk<Context>().osDetectionCheck(conditional)

        // When
        val result = osDetectionRule.canRun() as SafeToRunReport.MultipleReports

        // Then
        with(result.reports.first() as SafeToRunReport.SafeToRunReportFailure) {
            assertThat(failureMessage).isNotNull()
            assertThat(failureMessage).contains(DODGY_MANUFACTURER)
        }
    }

    companion object {
        const val DODGY_MANUFACTURER = "dodgy manufacturer"
    }
}
