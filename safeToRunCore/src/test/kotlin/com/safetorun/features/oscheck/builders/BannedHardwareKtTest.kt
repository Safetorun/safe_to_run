package com.safetorun.features.oscheck.builders

import com.google.common.truth.Truth
import com.safetorun.features.oscheck.OSInformationQuery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class BannedHardwareKtTest : TestCase() {

    private val osInformationQuery = mockk<OSInformationQuery>()
    private val bannedHardwareCheck = osInformationQuery.bannedHardware(BANNED_HARDWARE)

    fun `test that when we have a banned hardware check with a banned hardware it fails`() {
        // Given
        every { osInformationQuery.hardware() } returns NOT_BANNED_HARDWARE

        // When Then
        Truth.assertThat(bannedHardwareCheck().failed).isFalse()
    }

    fun `test that when we have a banned hardware check without a banned hardware it passed`() {
        // Given
        every { osInformationQuery.hardware() } returns BANNED_HARDWARE

        // When Then
        Truth.assertThat(bannedHardwareCheck().failed).isTrue()
    }

    companion object {
        const val BANNED_HARDWARE = "banned"
        const val NOT_BANNED_HARDWARE = "not_banned"
    }
}
