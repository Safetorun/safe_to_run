package com.safetorun.features.oscheck

import com.google.common.truth.Truth.assertThat
import com.safetorun.features.oscheck.conditionals.notManufacturer
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase

internal class BannedManufacturerNameTest : TestCase() {

    @MockK
    lateinit var osInformationQuery: OSInformationQuery

    override fun setUp() {
        MockKAnnotations.init(this)
    }

    fun `test that banned manufacturer fails if exact match`() {
        // GivenOSDetectionCheckTest
        every { osInformationQuery.manufacturer() } returns DODGY_MANUFACTURER
        val rule = osInformationQuery.notManufacturer(DODGY_MANUFACTURER)

        // When
        val result = rule.invoke()

        // Then
        assertThat(result.failed).isTrue()
    }

    fun `test that banned manufacturer fails if passed in as upper`() {
        // Given
        every { osInformationQuery.manufacturer() } returns DODGY_MANUFACTURER.uppercase()
        val rule = osInformationQuery.notManufacturer(DODGY_MANUFACTURER)

        // When
        val result = rule.invoke()

        // Then
        assertThat(result.failed).isTrue()
    }

    fun `test that banned manufacturer passes if not equal`() {
        // Given
        every { osInformationQuery.manufacturer() } returns GOOD_MANUFACTURER
        val rule = osInformationQuery.notManufacturer(DODGY_MANUFACTURER)

        // When
        val result = rule.invoke()

        // Then
        assertThat(result.failed).isFalse()
    }

    companion object {
        const val DODGY_MANUFACTURER = "dodgy"
        const val GOOD_MANUFACTURER = "ok"
    }
}
