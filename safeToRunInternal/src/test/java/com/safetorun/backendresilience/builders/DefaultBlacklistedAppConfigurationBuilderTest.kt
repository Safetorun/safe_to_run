package com.safetorun.backendresilience.builders

import com.google.common.truth.Truth.assertThat
import com.safetorun.resilienceshared.dto.Severity
import org.junit.Test

internal class DefaultBlacklistedAppConfigurationBuilderTest {

    @Test
    fun `test that we can build a blacklisted app configuration with blacklisted apps`() {
        val builder = BlacklistedAppConfigurationBuilderOffDevice(Severity.Error)
            .apply {
                +BLACKLISTED_APP_1
                BLACKLISTED_APP_2.blacklistApp()
            }
            .build()

        assertThat(builder.blacklistedApps).containsAtLeast(BLACKLISTED_APP_1, BLACKLISTED_APP_2)
        assertThat(builder.severity).isEqualTo(Severity.Error)
    }

    @Test
    fun `test that we can build a blacklisted app configuration`() {
        val builder = BlacklistedAppConfigurationBuilderOffDevice(Severity.Warn)
            .build()

        assertThat(builder.blacklistedApps).isEmpty()
        assertThat(builder.severity).isEqualTo(Severity.Warn)
    }


    companion object {
        const val BLACKLISTED_APP_1 = "com.blacklisted.app"
        const val BLACKLISTED_APP_2 = "com.blacklisted.app.other"
    }
}
