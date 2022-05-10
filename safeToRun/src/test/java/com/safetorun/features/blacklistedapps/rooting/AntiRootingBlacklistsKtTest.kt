package com.safetorun.features.blacklistedapps.rooting

import com.google.common.truth.Truth.assertThat
import com.safetorun.features.blacklistedapps.BlacklistedAppCheck
import com.safetorun.features.blacklistedapps.blacklistConfig
import com.safetorun.reporting.anyFailures
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class AntiRootingBlacklistsKtTest : TestCase() {

    fun `test that anti rooting package adds blacklisted rooting apps`() {
        val result = blacklistConfig(mockk<BlacklistedAppCheck>(relaxed = true).apply {
            every { isAppPresent(RootingAppConstants.SUPER_USER) } returns true
        }, mockk(relaxed = true)) {
            blacklistRootingApps()
        }.canRun().anyFailures()

        assertThat(result).isTrue()
    }
}
