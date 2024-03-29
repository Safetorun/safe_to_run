package com.safetorun.features.oscheck.emulators

import com.google.common.truth.Truth.assertThat
import com.safetorun.features.oscheck.OSInformationQuery
import com.safetorun.features.oscheck.OsCheckConstants
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class AvdEmulatorKtTest : TestCase() {

    private val osInformation = mockk<OSInformationQuery>()

    fun `test that avd emulator works as correctly if os type is unknown`() {
        // Given
        every { osInformation.bootloader() } returns OsCheckConstants.UNKNOWN
        every { osInformation.device() } returns NOT_BANNED
        every { osInformation.board() } returns NOT_BANNED

        // When Then
        assertThat(osInformation.banAvdEmulator()().failed).isTrue()
    }

    fun `test that avd emulator works as correctly if os type is device is generic`() {
        // Given
        every { osInformation.bootloader() } returns NOT_BANNED
        every { osInformation.board() } returns NOT_BANNED
        every { osInformation.device() } returns OsCheckConstants.AVD_DEVICE_TYPE

        // When Then
        assertThat(osInformation.banAvdEmulator()().failed).isTrue()
    }

    fun `test that avd emulator works as correctly if board is goldfish`() {
        // Given
        every { osInformation.bootloader() } returns NOT_BANNED
        every { osInformation.device() } returns NOT_BANNED
        every { osInformation.board() } returns OsCheckConstants.AVD_EMULATOR_BOARD

        // When Then
        assertThat(osInformation.banAvdEmulator()().failed).isTrue()
    }

    fun `test that avd emulator works as correctly if nothing is banned`() {
        // Given
        every { osInformation.bootloader() } returns NOT_BANNED
        every { osInformation.device() } returns NOT_BANNED
        every { osInformation.board() } returns NOT_BANNED

        // When Then
        assertThat(osInformation.banAvdEmulator()().failed).isFalse()
    }

    companion object {
        const val NOT_BANNED = "Not banned"
    }
}
