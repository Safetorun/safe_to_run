package com.safetorun.features.oscheck.emulator

import com.google.common.truth.Truth.assertThat
import com.safetorun.features.oscheck.OSInformationQuery
import com.safetorun.features.oscheck.OsCheckConstants
import com.safetorun.features.oscheck.OsCheckConstants.GENYMOTION_MANUFACTURER
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class EmulatorsKtTest : TestCase() {
    private val osCheck by lazy { mockk<OSInformationQuery>() }

    override fun setUp() {
        super.setUp()
        every { osCheck.manufacturer() } returns NOT_BANNED
    }

    fun `test that avd emulator check fails if bootloader is unknown`() {
        every { osCheck.bootloader() } returns OsCheckConstants.UNKNOWN
        every { osCheck.device() } returns OsCheckConstants.AVD_DEVICE_TYPE
        every { osCheck.board() } returns NOT_BANNED


        assertThat(banAvdEmulatorCheck(osCheck)).isTrue()
    }

    fun `test that avd emulator check fails if device is banned`() {
        every { osCheck.bootloader() } returns NOT_BANNED
        every { osCheck.device() } returns OsCheckConstants.AVD_DEVICE_TYPE
        every { osCheck.board() } returns NOT_BANNED

        assertThat(banAvdEmulatorCheck(osCheck)).isTrue()
    }

    fun `test that avd emulator check fails if board is banned`() {
        every { osCheck.bootloader() } returns NOT_BANNED
        every { osCheck.device() } returns NOT_BANNED
        every { osCheck.board() } returns OsCheckConstants.AVD_EMULATOR_BOARD

        assertThat(banAvdEmulatorCheck(osCheck)).isTrue()
    }

    fun `test that avd emulator check passes if it passes`() {
        every { osCheck.bootloader() } returns NOT_BANNED
        every { osCheck.device() } returns NOT_BANNED
        every { osCheck.board() } returns NOT_BANNED

        assertThat(banAvdEmulatorCheck(osCheck)).isFalse()
    }

    fun `test that avd bluestacks check fails if it fails`() {
        every { osCheck.bootloader() } returns OsCheckConstants.UNKNOWN
        assertThat(banBluestacksEmulatorCheck(osCheck)).isTrue()
    }

    fun `test that bluestacks emulator check passes if it passes`() {
        every { osCheck.bootloader() } returns NOT_BANNED
        assertThat(banBluestacksEmulatorCheck(osCheck)).isFalse()
    }

    fun `test that genymotion emulator check fails if bootloader check fails`() {
        every { osCheck.bootloader() } returns OsCheckConstants.UNKNOWN
        every { osCheck.manufacturer() } returns NOT_BANNED
        every { osCheck.board() } returns NOT_BANNED


        assertThat(banGenymotionEmulatorCheck(osCheck)).isTrue()
    }

    fun `test that genymotion emulator check fails if manufacturer check fails`() {

        every { osCheck.bootloader() } returns NOT_BANNED
        every { osCheck.manufacturer() } returns GENYMOTION_MANUFACTURER
        every { osCheck.board() } returns NOT_BANNED

        assertThat(banGenymotionEmulatorCheck(osCheck)).isTrue()
    }

    fun `test that genymotion emulator check fails if board check fails`() {
        every { osCheck.bootloader() } returns NOT_BANNED
        every { osCheck.manufacturer() } returns NOT_BANNED
        every { osCheck.board() } returns OsCheckConstants.UNKNOWN

        assertThat(banGenymotionEmulatorCheck(osCheck)).isTrue()
    }

    fun `test that genymotion emulator check passes if it passes`() {
        every { osCheck.bootloader() } returns NOT_BANNED
        every { osCheck.manufacturer() } returns NOT_BANNED
        every { osCheck.board() } returns NOT_BANNED

        assertThat(banGenymotionEmulatorCheck(osCheck)).isFalse()
    }

    // Xiaomi checks
    fun `test that avd emulator check passes if bootloader is unknown but device is Xiaomi`() {
        every { osCheck.bootloader() } returns OsCheckConstants.UNKNOWN
        every { osCheck.manufacturer() } returns OsCheckConstants.XIAOMI
        every { osCheck.board() } returns NOT_BANNED
        every { osCheck.device() } returns NOT_BANNED

        assertThat(banAvdEmulatorCheck(osCheck)).isFalse()
    }


    fun `test that genymotion emulator check passes if it passes but device is Xiaomi`() {
        every { osCheck.bootloader() } returns OsCheckConstants.UNKNOWN
        every { osCheck.manufacturer() } returns OsCheckConstants.XIAOMI
        every { osCheck.board() } returns NOT_BANNED
        every { osCheck.device() } returns NOT_BANNED

        assertThat(banGenymotionEmulatorCheck(osCheck)).isFalse()
    }

    fun `test that avd bluestacks check passes if it fails but device is Xiaomi`() {
        every { osCheck.bootloader() } returns OsCheckConstants.UNKNOWN
        every { osCheck.manufacturer() } returns OsCheckConstants.XIAOMI
        assertThat(banBluestacksEmulatorCheck(osCheck)).isFalse()
    }


    companion object {
        const val NOT_BANNED = "Not banned"
    }
}
