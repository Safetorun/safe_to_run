package io.github.dllewellyn.safetorun.features.blacklistedapps.rooting

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.features.blacklistedapps.BlacklistedAppCheck
import io.github.dllewellyn.safetorun.features.blacklistedapps.blacklistConfig
import io.github.dllewellyn.safetorun.reporting.anyFailures
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
