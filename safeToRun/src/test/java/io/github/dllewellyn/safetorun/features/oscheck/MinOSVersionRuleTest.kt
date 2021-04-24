package io.github.dllewellyn.safetorun.features.oscheck

import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase


internal class MinOSVersionRuleTest : TestCase() {

    @MockK
    lateinit var osInformationQuery: OSInformationQuery

    override fun setUp() {
        MockKAnnotations.init(this)
    }

    fun `test that min os version fails if too low`() {
        // Given
        every { osInformationQuery.osVersion() } returns 29
        val query = osInformationQuery.minOsVersion(30)

        // When
        val result = query.invoke()

        // Then
        assertThat(result.failed).isTrue()
    }


    fun `test that min os version passes if equal`() {
        // Given
        every { osInformationQuery.osVersion() } returns 30
        val query = osInformationQuery.minOsVersion(30)

        // When
        val result = query.invoke()

        // Then
        assertThat(result.failed).isFalse()
    }


    fun `test that min os version passes if higher`() {
        // Given
        every { osInformationQuery.osVersion() } returns 31
        val query = osInformationQuery.minOsVersion(30)

        // When
        val result = query.invoke()

        // Then
        assertThat(result.failed).isFalse()
    }
}