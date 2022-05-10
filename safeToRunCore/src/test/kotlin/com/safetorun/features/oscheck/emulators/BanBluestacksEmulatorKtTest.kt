package com.safetorun.features.oscheck.emulators

import com.google.common.truth.Truth
import com.safetorun.features.oscheck.OSInformationQuery
import com.safetorun.features.oscheck.OsCheckConstants
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class BanBluestacksEmulatorKtTest : TestCase() {
    private val osInformation = mockk<OSInformationQuery>()

    fun `test that avd emulator works as correctly if bootloader is unknown`() {
        // Given
        every { osInformation.bootloader() } returns OsCheckConstants.UNKNOWN

        // When Then
        Truth.assertThat(osInformation.banBluestacksEmulator()().failed).isTrue()
    }

    fun `test that avd emulator works as correctly if nothing is banned`() {
        // Given
        every { osInformation.bootloader() } returns NOT_BANNED

        // When Then
        Truth.assertThat(osInformation.banBluestacksEmulator()().failed).isFalse()
    }

    companion object {
        const val NOT_BANNED = "Not banned"
    }
}
