package io.github.dllewellyn.safetorun.features.oscheck.emulator

import android.os.Build
import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.features.oscheck.OsCheckConstants
import io.github.dllewellyn.safetorun.utils.mockBuildField
import junit.framework.TestCase

internal class EmulatorsKtTest : TestCase() {

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

        assertThat(banBluestacksEmulatorCheck()).isFalse()
    }

    companion object {
        const val NOT_BANNED = "Not banned"
    }
}
