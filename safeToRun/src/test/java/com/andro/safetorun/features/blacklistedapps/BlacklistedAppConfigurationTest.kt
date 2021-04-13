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
        every { blacklistedAppCheck.isAppPresent(IS_PRESENT_PACKAGE) } returns true
        every { blacklistedAppCheck.isAppPresent(NOT_PRESENT_PACKAGE) } returns false
    }

    @Test
    fun `we configure our blacklisted app to fail if com abc com exists can run should fail`() {
        val conf = blacklistConfiguration(blacklistedAppCheck) {
            +IS_PRESENT_PACKAGE
        }
        assertThat(conf.canRun(mockk(relaxed = true))).isFalse()
    }

    @Test
    fun `we configure our blacklisted app to fail with def can run should succeed`() {
        val conf = blacklistConfiguration(blacklistedAppCheck) {
            +NOT_PRESENT_PACKAGE
        }

        assertThat(conf.canRun(mockk(relaxed = true))).isTrue()
    }

    @Test
    fun `we configure our blacklisted app to fail with both can run should fail`() {
        val conf = blacklistConfiguration(blacklistedAppCheck) {
            +NOT_PRESENT_PACKAGE
            +IS_PRESENT_PACKAGE
        }

        assertThat(conf.canRun(mockk(relaxed = true))).isFalse()
    }

    companion object {
        private const val IS_PRESENT_PACKAGE = "com.abc.com"
        private const val NOT_PRESENT_PACKAGE = "com.abc.def"
    }


}