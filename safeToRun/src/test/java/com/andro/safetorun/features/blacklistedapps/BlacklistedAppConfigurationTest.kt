package com.andro.safetorun.features.blacklistedapps

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test


class BlacklistedAppConfigurationTest {

    private val blacklistedAppCheck = mockk<BlacklistedAppCheck>(relaxed = true)

    @Before
    fun setup() {
        every { blacklistedAppCheck.isAppPresent("com.abc.com") } returns true
        every { blacklistedAppCheck.isAppPresent("com.abc.def") } returns false
    }

    @Test
    fun `we configure our blacklisted app to fail if com abc com exists can run should fail`() {
        val conf = blacklistConfiguration(blacklistedAppCheck) {
            +"com.abc.com"
        }
        assertThat(conf.canRun(mockk(relaxed = true))).isFalse()
    }

    @Test
    fun `we configure our blacklisted app to fail with def can run should succeed`() {
        val conf = blacklistConfiguration(blacklistedAppCheck) {
            +"com.abc.def"
        }

        assertThat(conf.canRun(mockk(relaxed = true))).isTrue()
    }

    @Test
    fun `we configure our blacklisted app to fail with both can run should fail`() {
        val conf = blacklistConfiguration(blacklistedAppCheck) {
            +"com.abc.def"
            +"com.abc.com"
        }

        assertThat(conf.canRun(mockk(relaxed = true))).isFalse()
    }

}