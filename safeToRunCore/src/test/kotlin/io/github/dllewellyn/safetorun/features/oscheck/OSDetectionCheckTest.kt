package io.github.dllewellyn.safetorun.features.oscheck

import com.google.common.truth.Truth
import io.github.dllewellyn.safetorun.checks.SafeToRunCheck
import io.github.dllewellyn.safetorun.conditional.Conditional
import io.github.dllewellyn.safetorun.conditional.ConditionalResponse
import io.github.dllewellyn.safetorun.conditional.conditionalBuilder
import io.github.dllewellyn.safetorun.features.oscheck.builders.minOsVersion
import io.github.dllewellyn.safetorun.features.oscheck.conditionals.BannedManufacturerName
import io.github.dllewellyn.safetorun.features.oscheck.conditionals.notManufacturer
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class OSDetectionCheckTest : TestCase() {

    private val osInformationQuery = mockk<OSInformationQuery>()
    private val osInformationStrings = mockk<OSDetectionStrings>()

    override fun setUp() {
        every { osInformationStrings.genericFailureMessage() } returns "Failed"
        every { osInformationStrings.genericPassMessage() } returns "Passed"
    }

    fun `test that we can create a os version rule that fails if os is too low`() {
        // Given
        every { osInformationQuery.osVersion() } returns 29

        // When
        val result = osDetectionCheck(
            osInformationQuery.minOsVersion(
                30
            )
        ).canRun() as SafeToRunReport.MultipleReports

        // Then
        with(result.reports.first() as SafeToRunReport.SafeToRunReportFailure) {
            Truth.assertThat(failureMessage).isNotNull()
            Truth.assertThat(failureMessage).contains("30")
            Truth.assertThat(failureMessage).contains("29")
        }
    }

    fun `test that we can create a os version rule that passes if os is high enough`() {
        // Given
        every { osInformationQuery.osVersion() } returns 30

        // When
        val result = osDetectionCheck(
            osInformationQuery.minOsVersion(30)
        ).canRun() as SafeToRunReport.SafeToRunReportSuccess

        // Then
        Truth.assertThat(result.successMessage).isNotNull()
    }

    fun `test that we can create a rule that will fail if manufacturer is dodgy`() {
        // Given
        every { osInformationQuery.manufacturer() } returns DODGY_MANUFACTURER

        // When
        val result = osDetectionCheck(
            BannedManufacturerName(
                DODGY_MANUFACTURER,
                osInformationQuery
            )
        ).canRun() as SafeToRunReport.MultipleReports

        // Then
        with(result.reports.first() as SafeToRunReport.SafeToRunReportFailure) {
            Truth.assertThat(failureMessage).isNotNull()
            Truth.assertThat(failureMessage).contains(DODGY_MANUFACTURER)
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

        val osDetectionRule = osDetectionCheck(conditional)

        // When
        val result = osDetectionRule.canRun() as SafeToRunReport.MultipleReports

        // Then
        with(result.reports.first() as SafeToRunReport.SafeToRunReportFailure) {
            Truth.assertThat(failureMessage).isNotNull()
            Truth.assertThat(failureMessage).contains(DODGY_MANUFACTURER)
        }
    }

    fun `test that we can a generic message if failing with a null conditional message`() {
        // Given
        val builder = conditionalBuilder {
            with { ConditionalResponse(true, null) }
        }

        val rule = osDetectionCheck(builder)

        // When
        val result = rule.canRun() as SafeToRunReport.MultipleReports

        // Then
        with(result.reports.first() as SafeToRunReport.SafeToRunReportFailure) {
            Truth.assertThat(failureMessage).isEqualTo(osInformationStrings.genericFailureMessage())
        }
    }

    private fun osDetectionCheck(vararg conditional: Conditional): SafeToRunCheck {
        return OSDetectionCheck(OSDetectionConfig(conditional.toList()), osInformationStrings)
    }

    companion object {
        const val DODGY_MANUFACTURER = "dodgy manufacturer"
    }
}
