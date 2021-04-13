package com.andro.safetorun.features.oscheck

import com.andro.safetorun.conditional.conditionalBuilder
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class OSDetectionCheckTest {

    @MockK
    lateinit var osInformationQuery: OSInformationQuery

    @Before
    fun before() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test that we can create a os version rule that fails if os is too low`() {
        // Given
        every { osInformationQuery.osVersion() } returns 29
        val osDetectionRule = OSDetectionConfig(listOf(MinOSVersionRule(30, osInformationQuery)))

        // When
        val result = osDetection(osDetectionRule).canRun(mockk(relaxed = true))

        // Then
        assertThat(result).isFalse()
    }

    @Test
    fun `test that we can create a os version rule that passes if os is high enough`() {
        // Given
        every { osInformationQuery.osVersion() } returns 30
        val osDetectionRule = OSDetectionConfig(listOf(MinOSVersionRule(30, osInformationQuery)))

        // When
        val result = osDetection(osDetectionRule).canRun(mockk(relaxed = true))

        // Then
        assertThat(result).isTrue()
    }

    @Test
    fun `test that we can create a rule that will fail if manufacturer is dodgy`() {
        // Given
        every { osInformationQuery.manufacturer() } returns DODGY_MANUFACTURER

        val osDetectionRule =
            OSDetectionConfig(listOf(BannedManufacturerName(DODGY_MANUFACTURER, osInformationQuery)))

        // When
        val result = osDetection(osDetectionRule).canRun(mockk(relaxed = true))

        // Then
        assertThat(result).isFalse()

    }

    @Test
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
        val result = osDetection(osDetectionRule).canRun(mockk(relaxed = true))

        // Then
        assertThat(result).isFalse()
    }

    private fun osDetection(osDetectionConfig: OSDetectionConfig) =
        OSDetectionCheck(osDetectionConfig)

    companion object {
        const val DODGY_MANUFACTURER = "dodgy manufacturer"
    }
}