package com.andro.safetorun.features.oscheck

import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test


internal class MinOSVersionRuleTest {

    @MockK
    lateinit var osInformationQuery: OSInformationQuery

    @Before
    fun before() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test that min os version fails if too low`() {
        // Given
        every { osInformationQuery.osVersion() } returns 29
        val query = osInformationQuery.minOsVersion(30)

        // When
        val result = query.invoke()

        // Then
        assertThat(result).isFalse()
    }

    @Test
    fun `test that min os version passes if equal`() {
        // Given
        every { osInformationQuery.osVersion() } returns 30
        val query = osInformationQuery.minOsVersion(30)

        // When
        val result = query.invoke()

        // Then
        assertThat(result).isTrue()
    }

    @Test
    fun `test that min os version passes if higher`() {
        // Given
        every { osInformationQuery.osVersion() } returns 31
        val query = osInformationQuery.minOsVersion(30)

        // When
        val result = query.invoke()

        // Then
        assertThat(result).isTrue()
    }
}