package com.safetorun.features.oscheck.builders

import com.google.common.truth.Truth
import com.safetorun.features.oscheck.OSInformationQuery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class BannedHostKtTest : TestCase() {

    private val osInformationQuery = mockk<OSInformationQuery>()
    private val bannedHardwareCheck = osInformationQuery.bannedHost(BANNED_HOST)

    fun `test that when we have a banned host check with a banned host it fails`() {
        // Given
        every { osInformationQuery.host() } returns NOT_BANNED_HOST

        // When Then
        Truth.assertThat(bannedHardwareCheck().failed).isFalse()
    }

    fun `test that when we have a banned host check without a banned host it passed`() {
        // Given
        every { osInformationQuery.host() } returns BANNED_HOST

        // When Then
        Truth.assertThat(bannedHardwareCheck().failed).isTrue()
    }

    companion object {
        const val BANNED_HOST = "banned"
        const val NOT_BANNED_HOST = "not_banned"
    }
}
