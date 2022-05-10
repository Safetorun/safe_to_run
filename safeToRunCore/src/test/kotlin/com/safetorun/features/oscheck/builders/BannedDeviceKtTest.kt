package com.safetorun.features.oscheck.builders

import com.google.common.truth.Truth
import com.safetorun.features.oscheck.OSInformationQuery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class BannedDeviceKtTest : TestCase() {

    private val osInformationQuery = mockk<OSInformationQuery>()
    private val bannedDeviceCheck = osInformationQuery.bannedDevice(BANNED_DEVICE)

    fun `test that when we have a device model check with a banned device it fails`() {
        // Given
        every { osInformationQuery.device() } returns NOT_BANNED_DEVICE

        // When Then
        Truth.assertThat(bannedDeviceCheck().failed).isFalse()
    }

    fun `test that when we have a banned device check without a banned device it passed`() {
        // Given
        every { osInformationQuery.device() } returns BANNED_DEVICE

        // When Then
        Truth.assertThat(bannedDeviceCheck().failed).isTrue()
    }

    companion object {
        const val BANNED_DEVICE = "banned"
        const val NOT_BANNED_DEVICE = "not_banned"
    }
}
