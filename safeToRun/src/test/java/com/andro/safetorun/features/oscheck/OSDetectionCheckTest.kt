package com.andro.safetorun.features.oscheck

import com.andro.safetorun.conditional.conditionalBuilder
import com.andro.safetorun.reporting.SafeToRunReport
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase

class OSDetectionCheckTest : TestCase() {

    @MockK
    lateinit var osInformationQuery: OSInformationQuery

    override fun setUp() {
        MockKAnnotations.init(this)
    }

    fun `test that we can create a os version rule that fails if os is too low`() {
        // Given
        every { osInformationQuery.osVersion() } returns 29
        val osDetectionRule = OSDetectionConfig(listOf(MinOSVersionRule(30, osInformationQuery)))

        // When
        val result = osDetection(osDetectionRule).canRun(mockk(relaxed = true)) as SafeToRunReport.MultipleReports

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
        val osDetectionRule = OSDetectionConfig(listOf(MinOSVersionRule(30, osInformationQuery)))

        // When
        val result =
            osDetection(osDetectionRule).canRun(mockk(relaxed = true)) as SafeToRunReport.SafeToRunReportSuccess

        // Then
        assertThat(result.successMessage).isNotNull()
    }


    fun `test that we can create a rule that will fail if manufacturer is dodgy`() {
        // Given
        every { osInformationQuery.manufacturer() } returns DODGY_MANUFACTURER

        val osDetectionRule =
            OSDetectionConfig(listOf(BannedManufacturerName(DODGY_MANUFACTURER, osInformationQuery)))

        // When
        val result = osDetection(osDetectionRule).canRun(mockk(relaxed = true)) as SafeToRunReport.MultipleReports

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

        val osDetectionRule = OSDetectionConfig(listOf(conditional))

        // When
        val result = osDetection(osDetectionRule).canRun(mockk(relaxed = true)) as SafeToRunReport.MultipleReports

        // Then
        with(result.reports.first() as SafeToRunReport.SafeToRunReportFailure) {
            assertThat(failureMessage).isNotNull()
            assertThat(failureMessage).contains(DODGY_MANUFACTURER)
        }
    }

    private fun osDetection(osDetectionConfig: OSDetectionConfig) =
        OSDetectionCheck(osDetectionConfig)

    companion object {
        const val DODGY_MANUFACTURER = "dodgy manufacturer"
    }
}