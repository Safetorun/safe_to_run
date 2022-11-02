package com.safetorun.resilienceshared.builders

import com.google.common.truth.Truth
import org.junit.Test

internal class BaseBlacklistedAppConfigurationBuilderTest {
    @Test
    fun `test that we can build a blacklisted app configuration with blacklisted apps`() {
        val builder = BaseBlacklistedAppConfigurationBuilder()
            .apply {
                +BLACKLISTED_APP_1
                BLACKLISTED_APP_2.blacklistApp()
            }
            .build()

        Truth.assertThat(builder.blacklistedApps)
            .containsAtLeast(BLACKLISTED_APP_1, BLACKLISTED_APP_2)
    }

    @Test
    fun `test that we can build a blacklisted app configuration`() {
        val builder = BaseBlacklistedAppConfigurationBuilder()
            .build()

        Truth.assertThat(builder.blacklistedApps).isEmpty()
    }


    companion object {
        const val BLACKLISTED_APP_1 = "com.blacklisted.app"
        const val BLACKLISTED_APP_2 = "com.blacklisted.app.other"
    }
}
