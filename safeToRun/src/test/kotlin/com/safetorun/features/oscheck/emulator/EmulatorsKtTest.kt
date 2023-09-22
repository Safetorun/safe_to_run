package com.safetorun.features.oscheck.emulator

import android.os.Build
import com.google.common.truth.Truth.assertThat
import com.safetorun.features.oscheck.OsCheckConstants
import com.safetorun.utils.mockBuildField
import junit.framework.TestCase

internal class EmulatorsKtTest : TestCase() {

    override fun setUp() {
        super.setUp()
        mockBuildField(NOT_BANNED, "MANUFACTURER", Build::class.java)
    }

    fun `test that avd emulator check fails if bootloader is unknown`() {
        mockBuildField(OsCheckConstants.UNKNOWN, "BOOTLOADER", Build::class.java)
        mockBuildField(NOT_BANNED, "DEVICE", Build::class.java)
        mockBuildField(NOT_BANNED, "BOARD", Build::class.java)
        assertThat(banAvdEmulatorCheck()).isTrue()
    }

    fun `test that avd emulator check fails if device is banned`() {
        mockBuildField(NOT_BANNED, "BOOTLOADER", Build::class.java)
        mockBuildField(OsCheckConstants.AVD_DEVICE_TYPE, "DEVICE", Build::class.java)
        mockBuildField(NOT_BANNED, "BOARD", Build::class.java)

        assertThat(banAvdEmulatorCheck()).isTrue()
    }

    fun `test that avd emulator check fails if board is banned`() {
        mockBuildField(NOT_BANNED, "BOOTLOADER", Build::class.java)
        mockBuildField(NOT_BANNED, "DEVICE", Build::class.java)
        mockBuildField(OsCheckConstants.AVD_EMULATOR_BOARD, "BOARD", Build::class.java)

        assertThat(banAvdEmulatorCheck()).isTrue()
    }

    fun `test that avd emulator check passes if it passes`() {
        mockBuildField(NOT_BANNED, "BOOTLOADER", Build::class.java)
        mockBuildField(NOT_BANNED, "DEVICE", Build::class.java)
        mockBuildField(NOT_BANNED, "BOARD", Build::class.java)
        assertThat(banAvdEmulatorCheck()).isFalse()
    }

    fun `test that avd bluestacks check fails if it fails`() {
        mockBuildField(OsCheckConstants.UNKNOWN, "BOOTLOADER", Build::class.java)
        assertThat(banBluestacksEmulatorCheck()).isTrue()
    }

    fun `test that bluestacks emulator check passes if it passes`() {
        mockBuildField(NOT_BANNED, "BOOTLOADER", Build::class.java)
        assertThat(banBluestacksEmulatorCheck()).isFalse()
    }

    fun `test that genymotion emulator check fails if bootloader check fails`() {
        mockBuildField(OsCheckConstants.UNKNOWN, "BOOTLOADER", Build::class.java)
        mockBuildField(NOT_BANNED, "MANUFACTURER", Build::class.java)
        mockBuildField(NOT_BANNED, "BOARD", Build::class.java)

        assertThat(banGenymotionEmulatorCheck()).isTrue()
    }

    fun `test that genymotion emulator check fails if manufacturer check fails`() {
        mockBuildField(NOT_BANNED, "BOOTLOADER", Build::class.java)
        mockBuildField(OsCheckConstants.GENYMOTION_MANUFACTURER, "MANUFACTURER", Build::class.java)
        mockBuildField(NOT_BANNED, "BOARD", Build::class.java)

        assertThat(banGenymotionEmulatorCheck()).isTrue()
    }

    fun `test that genymotion emulator check fails if board check fails`() {
        mockBuildField(NOT_BANNED, "BOOTLOADER", Build::class.java)
        mockBuildField(NOT_BANNED, "MANUFACTURER", Build::class.java)
        mockBuildField(OsCheckConstants.UNKNOWN, "BOARD", Build::class.java)

        assertThat(banGenymotionEmulatorCheck()).isTrue()
    }

    fun `test that genymotion emulator check passes if it passes`() {
        mockBuildField(NOT_BANNED, "BOOTLOADER", Build::class.java)
        mockBuildField(NOT_BANNED, "MANUFACTURER", Build::class.java)
        mockBuildField(NOT_BANNED, "BOARD", Build::class.java)

        assertThat(banGenymotionEmulatorCheck()).isFalse()
    }

    // Xiaomi checks
    fun `test that avd emulator check passes if bootloader is unknown but device is Xiaomi`() {
        mockBuildField(OsCheckConstants.UNKNOWN, "BOOTLOADER", Build::class.java)
        mockBuildField(NOT_BANNED, "DEVICE", Build::class.java)
        mockBuildField(NOT_BANNED, "BOARD", Build::class.java)
        mockBuildField(OsCheckConstants.XIAOMI, "MANUFACTURER", Build::class.java)

        assertThat(banAvdEmulatorCheck()).isFalse()
    }


    fun `test that genymotion emulator check passes if it passes but device is Xiaomi`() {
        mockBuildField(OsCheckConstants.UNKNOWN, "BOOTLOADER", Build::class.java)
        mockBuildField(NOT_BANNED, "MANUFACTURER", Build::class.java)
        mockBuildField(NOT_BANNED, "BOARD", Build::class.java)

        mockBuildField(OsCheckConstants.XIAOMI, "MANUFACTURER", Build::class.java)

        assertThat(banGenymotionEmulatorCheck()).isFalse()
    }

    fun `test that avd bluestacks check passes if it fails but device is Xiaomi`() {
        mockBuildField(OsCheckConstants.UNKNOWN, "BOOTLOADER", Build::class.java)

        mockBuildField(OsCheckConstants.XIAOMI, "MANUFACTURER", Build::class.java)
        assertThat(banBluestacksEmulatorCheck()).isFalse()
    }


    companion object {
        const val NOT_BANNED = "Not banned"
    }
}
