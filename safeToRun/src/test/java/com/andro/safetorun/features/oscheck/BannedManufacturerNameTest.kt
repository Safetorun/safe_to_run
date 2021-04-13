package com.andro.safetorun.features.oscheck

import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

internal class BannedManufacturerNameTest {

    @MockK
    lateinit var osInformationQuery: OSInformationQuery

    @Before
    fun before() {
        MockKAnnotations.init(this)
    }


    @Test
    fun `test that banned manufacturer fails if exact match`() {
        // Given
        every { osInformationQuery.manufacturer() } returns DODGY_MANUFACTURER
        val rule = osInformationQuery.notManufacturer(DODGY_MANUFACTURER)

        // When
        val result = rule.invoke()

        // Then
        assertThat(result).isFalse()
    }

    @Test
    fun `test that banned manufacturer fails if passed in as upper`() {
        // Given
        every { osInformationQuery.manufacturer() } returns DODGY_MANUFACTURER.toUpperCase()
        val rule = osInformationQuery.notManufacturer(DODGY_MANUFACTURER)

        // When
        val result = rule.invoke()

        // Then
        assertThat(result).isFalse()

    }

    @Test
    fun `test that banned manufacturer passes if not equal`() {
        // Given
        every { osInformationQuery.manufacturer() } returns GOOD_MANUFACTURER
        val rule = osInformationQuery.notManufacturer(DODGY_MANUFACTURER)

        // When
        val result = rule.invoke()

        // Then
        assertThat(result).isTrue()
    }


    companion object {
        const val DODGY_MANUFACTURER = "dodgy"
        const val GOOD_MANUFACTURER = "ok"
    }
}